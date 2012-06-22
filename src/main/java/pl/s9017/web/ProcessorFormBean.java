package pl.s9017.web;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIForm;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.event.ComponentSystemEvent;
import javax.faces.model.ListDataModel;
import javax.faces.validator.ValidatorException;
import javax.inject.Inject;
import javax.inject.Named;

import pl.s9017.domain.Processor;
import pl.s9017.service.ProcessorManager;

@SessionScoped
@Named("processorBean")
public class ProcessorFormBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Processor processor = new Processor();
	private String klucz = null;
	
	public String getKlucz() {
		return klucz;
	}

	public void setKlucz(String klucz) {
		this.klucz = klucz;
	}

	private ListDataModel<Processor> processors = new ListDataModel<Processor>();
	
	@Inject
	private ProcessorManager pm;

	public Processor getProcessor() {
		return processor;
	}

	public void setProcessor(Processor processor) {
		this.processor = processor;
	}

	public ListDataModel<Processor> getAllProcessors() {
		processors.setWrappedData(pm.getAllProcessors());
		return processors;
	}
	
	public ListDataModel<Processor> getProcessorSearch() {
		processors.setWrappedData(pm.searchProcessor(processor));
		return processors;
	}

	// Actions
	public String addProcessor() {
		pm.addProcessor(processor);
		return "showDetalis";
		//return null;
	}
	public String showProcessor() {
		return "showProcessor";
		//return null;
	}
	public String Back() {
		pm.deleteProcessor(processor);
		return "addSimple";
		//return null;
	}
	public String searchProcessor() {
		processor.setKod(klucz);
		processor.setName(klucz);
		return "searchResult";
		 }
	
	public String editProcessor() {
		Processor processorToEdit = processors.getRowData();
		pm.deleteProcessor(processorToEdit);
		return "addSimple";
		 }


	public String deleteProcessor() {
		Processor processorToDelete = processors.getRowData();
		pm.deleteProcessor(processorToDelete);
		return null;
	}

	// Validators

	// Business logic validation
	public void uniqueKod(FacesContext context, UIComponent component,
			Object value) {

		String kod = (String) value;

		for (Processor processor : pm.getAllProcessors()) {
			if (processor.getKod().equalsIgnoreCase(kod)) {
				FacesMessage message = new FacesMessage(
						"Processor o takim kodzie juz jest w bazie");
				message.setSeverity(FacesMessage.SEVERITY_ERROR);
				throw new ValidatorException(message);
			}
		}
	}


	public void validateKodDob(ComponentSystemEvent event) {

		UIForm form = (UIForm) event.getComponent();
		UIInput kod = (UIInput) form.findComponent("kod");
		UIInput dob = (UIInput) form.findComponent("dob");

		if (kod.getValue() != null && dob.getValue() != null
				&& kod.getValue().toString().length() >= 2) {
			String twoDigitsOfKod = kod.getValue().toString().substring(0, 2);
			Calendar cal = Calendar.getInstance();
			cal.setTime(((Date) dob.getValue()));

			String lastDigitsOfDob = ((Integer) cal.get(Calendar.YEAR))
					.toString().substring(2);

			if (!twoDigitsOfKod.equals(lastDigitsOfDob)) {
				FacesContext context = FacesContext.getCurrentInstance();
				context.addMessage(form.getClientId(), new FacesMessage(
						"PIN doesn't match date of birth"));
				context.renderResponse();
			}
		}
	}
}
