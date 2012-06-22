package pl.s9017.service;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;

import pl.s9017.domain.Processor;

@ApplicationScoped
public class ProcessorManager {
	private List<Processor> db = new ArrayList<Processor>();

	public void addProcessor(Processor processor) {
		Processor newProcessor = new Processor();

		newProcessor.setName(processor.getName());
		newProcessor.setSlot(processor.getSlot());
		newProcessor.setKod(processor.getKod());
		newProcessor.setDateOfBirth(processor.getDateOfBirth());
		newProcessor.setGHz(processor.getGHz());
		newProcessor.setNumOfCore(processor.getNumOfCore());

		db.add(newProcessor);
	}

	public void deleteProcessor(Processor processor) {
		Processor processorToRemove = null;
		for (Processor p : db) {
			if (processor.getName().equals(p.getName())) {
				processorToRemove = p;
				break;
			}
		}
		if (processorToRemove != null)
			db.remove(processorToRemove);
	}

	public List<Processor> getAllProcessors() {
		return db;
	}

	public List<Processor> searchProcessor(Processor p) {
			List<Processor> lista = new ArrayList<Processor>();
			for (Processor procesor : db) {
				if (p.getKod().equals(procesor.getKod()) || p.getName().equals(procesor.getName())) {
				lista.add(p);
				}
			}
			return lista;
	}
}
