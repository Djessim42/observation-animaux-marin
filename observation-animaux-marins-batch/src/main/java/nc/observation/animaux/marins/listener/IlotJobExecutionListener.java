package nc.observation.animaux.marins.listener;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.stereotype.Component;

@Component
public class IlotJobExecutionListener implements JobExecutionListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(IlotJobExecutionListener.class);

    @Override
    public void beforeJob(JobExecution jobExecution) {
        LOGGER.info("Démarrage du job d'import des ilots");
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        if (jobExecution.getExitStatus() == ExitStatus.COMPLETED) {
            LOGGER.info("Job d'import des ilots terminé avec succès");
        } else {
            LOGGER.warn("Un souci est survenu lors du d'import des ilots");
        }
    }
}
