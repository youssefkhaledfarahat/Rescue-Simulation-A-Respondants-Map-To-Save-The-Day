package model.units;

import model.disasters.Infection;
import model.events.WorldListener;
import model.infrastructure.ResidentialBuilding;
import model.people.Citizen;
import model.people.CitizenState;
import simulation.Address;
import simulation.Rescuable;
import exceptions.CannotTreatException;
import exceptions.IncompatibleTargetException;

public class Ambulance extends MedicalUnit {

	public Ambulance(String unitID, Address location, int stepsPerCycle,
			WorldListener worldListener) {
		super(unitID, location, stepsPerCycle, worldListener);
	}

	@Override
	public void treat() {
		getTarget().getDisaster().setActive(false);

		Citizen target = (Citizen) getTarget();
		if (target.getHp() == 0) {
			jobsDone();
			return;
		} else if (target.getBloodLoss() > 0) {
			target.setBloodLoss(target.getBloodLoss() - getTreatmentAmount());
			if (target.getBloodLoss() == 0)
				target.setState(CitizenState.RESCUED);
		}

		else if (target.getBloodLoss() == 0)

			heal();

	}

	public void respond(Rescuable r) throws CannotTreatException, IncompatibleTargetException {
		if(r instanceof ResidentialBuilding){
			if(this instanceof Ambulance){
				throw new IncompatibleTargetException(this, r, "The unit assigned to the target is incompatible to it.");
			}
		}
		if(r instanceof Citizen){
			if(r.getDisaster() instanceof Infection){
				throw new CannotTreatException(this, r, "cannot treat this citizen");
			}
			if(!canTreat(r)){
				throw new CannotTreatException(this, r, "cannot treat this citizen");
			}
		}
		if (getTarget() != null && ((Citizen) getTarget()).getBloodLoss() > 0
				&& getState() == UnitState.TREATING)
			reactivateDisaster();
		finishRespond(r);
	}
	public String toString(){
		return "" +this.getUnitID()+"\n"+"ambulance"+"\n"+this.getLocation().getX()+this.getLocation().getY()+"\n"+this.getStepsPerCycle()+"\n"+this.getTarget()+"\n"+this.getState()+"\n";
	}

}
