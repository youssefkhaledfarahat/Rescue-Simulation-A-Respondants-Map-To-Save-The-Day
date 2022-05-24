package model.units;

import model.events.WorldListener;
import simulation.Address;


public abstract class FireUnit extends Unit {

	public FireUnit(String unitID, Address location, int stepsPerCycle,WorldListener worldListener) {
		super(unitID, location, stepsPerCycle,worldListener);
	}
	public String toString(){
		return "" +this.getUnitID()+"\n"+"fireunit"+"\n"+this.getLocation().getX()+this.getLocation().getY()+"\n"+this.getStepsPerCycle()+"\n"+this.getTarget()+"\n"+this.getState()+"\n";
	}	

}
