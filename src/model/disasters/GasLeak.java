package model.disasters;

import model.infrastructure.ResidentialBuilding;
import model.people.CitizenState;
import exceptions.BuildingAlreadyCollapsedException;
import exceptions.CitizenAlreadyDeadException;


public class GasLeak extends Disaster {

	public GasLeak(int startCycle, ResidentialBuilding target) {
		super(startCycle, target);
	}
	
	@Override
	public void strike() throws BuildingAlreadyCollapsedException, CitizenAlreadyDeadException 
	{
		
		ResidentialBuilding target= (ResidentialBuilding)getTarget();
		target.setGasLevel(target.getGasLevel()+10);
		super.strike();
	}
	@Override
	public void cycleStep() throws BuildingAlreadyCollapsedException {
		ResidentialBuilding target= (ResidentialBuilding)getTarget();
		if(target.getStructuralIntegrity() == 0){
			throw new BuildingAlreadyCollapsedException(this, "The targetted building has already collapsed");
		}
		target.setGasLevel(target.getGasLevel()+15);
		
	}
	public String toString(){
		return "GasLeak"+" ";
	}

}
