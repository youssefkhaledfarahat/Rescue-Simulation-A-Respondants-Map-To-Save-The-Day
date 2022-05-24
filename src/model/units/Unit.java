package model.units;

import model.disasters.Collapse;
import model.disasters.Disaster;
import model.disasters.Fire;
import model.disasters.GasLeak;
import model.events.SOSResponder;
import model.events.WorldListener;
import model.infrastructure.ResidentialBuilding;
import model.people.Citizen;
import model.people.CitizenState;
import simulation.Address;
import simulation.Rescuable;
import simulation.Simulatable;
import exceptions.CannotTreatException;
import exceptions.IncompatibleTargetException;

public abstract class Unit implements Simulatable, SOSResponder {
	private String unitID;
	private UnitState state;
	private Address location;
	private Rescuable target;
	private int distanceToTarget;
	private int stepsPerCycle;
	private WorldListener worldListener;

	public Unit(String unitID, Address location, int stepsPerCycle,
			WorldListener worldListener) {
		this.unitID = unitID;
		this.location = location;
		this.stepsPerCycle = stepsPerCycle;
		this.state = UnitState.IDLE;
		this.worldListener = worldListener;
	}

	public void setWorldListener(WorldListener listener) {
		this.worldListener = listener;
	}

	public WorldListener getWorldListener() {
		return worldListener;
	}

	public UnitState getState() {
		return state;
	}

	public void setState(UnitState state) {
		this.state = state;
	}

	public Address getLocation() {
		return location;
	}

	public void setLocation(Address location) {
		this.location = location;
	}

	public String getUnitID() {
		return unitID;
	}

	public Rescuable getTarget() {
		return target;
	}

	public int getStepsPerCycle() {
		return stepsPerCycle;
	}

	public void setDistanceToTarget(int distanceToTarget) {
		this.distanceToTarget = distanceToTarget;
	}

	@Override
	public void respond(Rescuable r) throws IncompatibleTargetException, CannotTreatException {
		if(r instanceof Citizen){
			if(this instanceof Evacuator){
				throw new IncompatibleTargetException(this, r, "The unit assigned to the target is incompatible to it. You tried to target a citizen with an evacuator.");
			}
			if(this instanceof FireTruck){
				throw new IncompatibleTargetException(this, r, "The unit assigned to the target is incompatible to it. You tried to target a citizen with a firetruck.");
			}
			if(this instanceof GasControlUnit){
				throw new IncompatibleTargetException(this, r, "The unit assigned to the target is incompatible to it. You tried to target a citizen with a gas control unit.");
			}
			
		}
		if(r instanceof ResidentialBuilding){
			if(this instanceof Ambulance || this instanceof DiseaseControlUnit){
				throw new IncompatibleTargetException(this, r, "The unit assigned to the target is incompatible to it.");
			}
			if(!canTreat(r)){
				throw new CannotTreatException(this, r, "Cannot treat this building; it either has no disaster active or there already is a unit targetting it.");
			}
			if(this instanceof Evacuator && !(r.getDisaster() instanceof Collapse))
				throw new CannotTreatException(this, r, "cannot treat the building");
			if(this instanceof GasControlUnit && !(r.getDisaster() instanceof GasLeak))
				throw new CannotTreatException(this, r, "cannot treat the building");
			if(this instanceof FireTruck && !(r.getDisaster() instanceof Fire))
				throw new CannotTreatException(this, r, "cannot treat the building");

		}
	
		if (target != null && state == UnitState.TREATING)
			reactivateDisaster();
		finishRespond(r);

	}

	public void reactivateDisaster() {
		Disaster curr = target.getDisaster();
		curr.setActive(true);
	}

	public void finishRespond(Rescuable r) {
		target = r;
		state = UnitState.RESPONDING;
		Address t = r.getLocation();
		distanceToTarget = Math.abs(t.getX() - location.getX())
				+ Math.abs(t.getY() - location.getY());

	}

	public abstract void treat();
	
	public boolean canTreat(Rescuable r){
		if(r.getDisaster() != null){
		if(r instanceof ResidentialBuilding){
			ResidentialBuilding r1 = (ResidentialBuilding) r;
			if(!r1.getDisaster().isActive())
				return false;
		}
		
		if(r instanceof Citizen){
			Citizen r2 = (Citizen) r;
			if((r2.getState().equals(CitizenState.SAFE)) || (r2.getState().equals(CitizenState.RESCUED)) || (r2.getState().equals(CitizenState.DECEASED)))
				return false;
		}
		return true;
		}
		return false;
	}

	public void cycleStep() {
		if (state == UnitState.IDLE)
			return;
		if (distanceToTarget > 0) {
			distanceToTarget = distanceToTarget - stepsPerCycle;
			if (distanceToTarget <= 0) {
				distanceToTarget = 0;
				Address t = target.getLocation();
				worldListener.assignAddress(this, t.getX(), t.getY());
			}
		} else {
			state = UnitState.TREATING;
			treat();
		}
	}

	public void jobsDone() {
		target = null;
		state = UnitState.IDLE;

	}
	
}
