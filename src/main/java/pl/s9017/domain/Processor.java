package pl.s9017.domain;

import java.util.Date;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class Processor {
	
	private String name = "unknown";
	private String slot = "";
	private String kod = "";
	private Date dateOfBirth = new Date();
	private double GHz;
	private int numOfCore;
	
	@Size(min = 2, max = 20)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Pattern(regexp = "[A-Z]{3}[0-9]{3}")
	public String getSlot() {
		return slot;
	}
	public void setSlot(String slot) {
		this.slot = slot;
	}
	
	@Size(min = 2)
	public String getKod() {
		return kod;
	}
	public void setKod(String kod) {
		this.kod = kod;
	}
	
	@Min(0)
	public int getNumOfCore() {
		return numOfCore;
	}
	public void setNumOfCore(int numOfCore) {
		this.numOfCore = numOfCore;
	}
	
	@Past
	public Date getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	
	@DecimalMin(value = "0")
	public double getGHz() {
		return GHz;
	}
	public void setGHz(double GHz) {
		this.GHz = GHz;
	}
}
