package nc.observation.animaux.marins.items;

import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import nc.observation.animaux.marins.entity.Ilot;
import nc.observation.animaux.marins.repository.IlotRepository;

@Component
public class ImportIlotItemWriter implements ItemWriter<Ilot> {

    private final IlotRepository ilotRepository;

    public ImportIlotItemWriter(IlotRepository ilotRepository) {
        this.ilotRepository = ilotRepository;
    }

    @Override
    public void write(Chunk<? extends Ilot> chunk) {
        ilotRepository.saveAll(chunk.getItems());
    }
}
