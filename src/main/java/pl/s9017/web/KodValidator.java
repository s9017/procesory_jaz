package pl.s9017.web;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("kodValidator")
public class KodValidator implements Validator {

	@Override
	public void validate(FacesContext context, UIComponent component, Object value)
			throws ValidatorException {		
		String kod = (String) value;
		
		if (kod.length() != 4) {
			FacesMessage message = new FacesMessage();
			message.setDetail("KOD musi składać się z 4 cyfr");
			message.setSummary("KOD musi składać się z 4 cyfr");
			message.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(message);
		}
	}
}
