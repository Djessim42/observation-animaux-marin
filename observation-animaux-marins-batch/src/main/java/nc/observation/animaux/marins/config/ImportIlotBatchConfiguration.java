package nc.observation.animaux.marins.config;

import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.Proxy.Type;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.web.client.RestTemplate;

import nc.observation.animaux.marins.dto.IlotDTO;
import nc.observation.animaux.marins.entity.Ilot;
import nc.observation.animaux.marins.items.ImportIlotItemProcessor;
import nc.observation.animaux.marins.items.ImportIlotItemReader;
import nc.observation.animaux.marins.items.ImportIlotItemWriter;
import nc.observation.animaux.marins.listener.IlotJobExecutionListener;

/**
 * Classe de configuration du job d'import des ilôts
 */
@Configuration
public class ImportIlotBatchConfiguration {

    @Bean
    @Qualifier("importIlotJob")
    public Job importIlotJob(JobRepository jobRepository,
        IlotJobExecutionListener listener,
        @Qualifier("importIlotStep") Step importIlotStep) {
        return new JobBuilder("import-ilot-job", jobRepository)
            .preventRestart()
            .incrementer(new RunIdIncrementer())
            .listener(listener)
            .start(importIlotStep)
            .build();
    }

    @Bean
    @Qualifier("importIlotStep")
    public Step importIlotStep(
        @Value("${application.batch.chunk:50}") int chunk,
        JobRepository jobRepository,
        PlatformTransactionManager platformTransactionManager,
        ImportIlotItemReader importIlotItemReader,
        ImportIlotItemProcessor importIlotItemProcessor,
        ImportIlotItemWriter importIlotItemWriter) {

        return new StepBuilder("import-ilot-step", jobRepository)
            .<IlotDTO, Ilot>chunk(chunk, platformTransactionManager)
            .reader(importIlotItemReader)
            .processor(importIlotItemProcessor)
            .writer(importIlotItemWriter)
            .build();
    }

    @Bean
    public RestTemplate restTemplate(ProxyProperties proxyProperties) {
        if (proxyProperties.isUse()) {
            Proxy proxy = new Proxy(Type.HTTP, new InetSocketAddress(proxyProperties.getHost(), proxyProperties.getPort()));
            SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
            requestFactory.setProxy(proxy);
            return new RestTemplate(requestFactory);
        }
        return new RestTemplate();
    }
}
