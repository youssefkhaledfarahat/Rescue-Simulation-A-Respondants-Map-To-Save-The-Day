package model.units;

import model.events.WorldListener;
import model.infrastructure.ResidentialBuilding;
import simulation.Address;

public class Evacuator extends PoliceUnit {

	public Evacuator(String unitID, Address location, int stepsPerCycle,
			WorldListener worldListener, int maxCapacity) {
		super(unitID, location, stepsPerCycle, worldListener, maxCapacity);

	}

	@Override
	public void treat() {
		ResidentialBuilding target = (ResidentialBuilding) getTarget();
		if (target.getStructuralIntegrity() == 0
				|| target.getOccupants().size() == 0) {
			jobsDone();
			return;
		}

		for (int i = 0; getPassengers().size() != getMaxCapacity()
				&& i < target.getOccupants().size(); i++) {
			getPassengers().add(target.getOccupants().remove(i));
			i--;
		}

		setDistanceToBase(target.getLocation().getX()
				+ target.getLocation().getY());

	}
	public String toString(){
		String x="";
		for(int i=0;i<this.getPassengers().size();i++){
			x+=this.getPassengers().get(i).toString();
		}
		return "" +this.getUnitID()+"\n"+"evacuator"+"\n"+this.getLocation().getX()+this.getLocation().getY()+"\n"+this.getStepsPerCycle()+"\n"+this.getTarget()+"\n"+this.getState()+"\n"+this.getPassengers().size()+"\n"+x+"\n";
	}

}
