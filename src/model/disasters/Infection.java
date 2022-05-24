package model.disasters;

import model.people.Citizen;
import model.people.CitizenState;
import exceptions.BuildingAlreadyCollapsedException;
import exceptions.CitizenAlreadyDeadException;


public class Infection extends Disaster {

	public Infection(int startCycle, Citizen target) {
		super(startCycle, target);
	}
@Override
public void strike() throws BuildingAlreadyCollapsedException, CitizenAlreadyDeadException 
{
	Citizen target = (Citizen)getTarget();
	target.setToxicity(target.getToxicity()+25);
	super.strike();
}
	@Override
	public void cycleStep() throws CitizenAlreadyDeadException {
		Citizen target = (Citizen)getTarget();
		target.setToxicity(target.getToxicity()+15);
		
	}
	public String toString(){
		return "Infection"+" ";
	}

}
