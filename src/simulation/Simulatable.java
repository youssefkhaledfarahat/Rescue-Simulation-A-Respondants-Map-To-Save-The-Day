package simulation;

import exceptions.BuildingAlreadyCollapsedException;
import exceptions.CannotTreatException;
import exceptions.CitizenAlreadyDeadException;
import exceptions.IncompatibleTargetException;

public interface Simulatable {
public void cycleStep() throws IncompatibleTargetException, CannotTreatException, CitizenAlreadyDeadException, BuildingAlreadyCollapsedException;
}
