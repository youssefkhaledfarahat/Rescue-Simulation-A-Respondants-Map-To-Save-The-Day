package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout; //imports GridLayout library
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton; //imports JButton library
import javax.swing.JFrame; //imports JFrame library
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import model.disasters.GasLeak;
import model.units.Ambulance;
import model.units.DiseaseControlUnit;
import model.units.Evacuator;
import model.units.FireTruck;
import model.units.GasControlUnit;
import controller.CommandCenter;
import exceptions.BuildingAlreadyCollapsedException;
import exceptions.CannotTreatException;
import exceptions.CitizenAlreadyDeadException;
import exceptions.IncompatibleTargetException;

public class ButtonGrid extends JFrame implements ActionListener {
	boolean flagcitizen = false;
	boolean flagbuilding = false;
	CommandCenter x = new CommandCenter();
	JButton evac = new JButton();
	JButton dc = new JButton();
	JButton fu = new JButton();
	JButton amb = new JButton();
	JButton gc = new JButton();
	JButton next = new JButton();
	JTextArea cur = new JTextArea("Current Cycle: ");
	JTextArea cas = new JTextArea("Total Casualties: ");
	JTextArea ad = new JTextArea("Active Disasters: ");
	JButton next5 = new JButton();
	JFrame frame = new JFrame();
	JTextArea Info = new JTextArea("Info: ");
	JButton[][] grid = new JButton[10][10];
	static JPanel panell = new JPanel(new GridLayout(10, 10));

	public ButtonGrid() throws Exception {
		Random rand = new Random();
		float r = rand.nextFloat();
		float g = rand.nextFloat();
		float b1 = rand.nextFloat();
		Color rcolor = new Color(r, g, b1);
		frame = new JFrame();
		frame.setTitle("Rescue Simulation");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(150, 60, 1000, 1000);
		frame.setSize(new Dimension(1600, 1025));
		frame.setLayout(null);
		frame.setResizable(false);
		frame.setVisible(true);
		JPanel Grid = new JPanel();

		panell.setBounds(200, 0, 1000, 1000);
		// //////BackGround
		JLabel back = new JLabel();
		back.setIcon(new ImageIcon("backmain.jpg"));
		frame.add(back);
		// /////INFO
		// Info.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));
		Info.setBounds(1200, 400, 600, 800);
		frame.add(Info);
		Info.setVisible(true);
		Info.setEditable(false);
		// ////UNITS & Next Cycle & Skip 5 Cycles & Current Cycle

		// evac.setPreferredSize(new Dimension(128,128));
		evac.setBounds(0, 0, 200, 200);
		evac.setIcon(new ImageIcon("evac.png"));
		evac.setVisible(true);
		evac.addActionListener(this);
		frame.add(evac);
		dc.setBounds(0, 200, 200, 200);
		dc.setIcon(new ImageIcon("dc.png"));
		dc.setVisible(true);
		dc.addActionListener(this);
		frame.add(dc);
		fu.setBounds(0, 400, 200, 200);
		fu.setIcon(new ImageIcon("fu.png"));
		fu.setVisible(true);
		fu.addActionListener(this);
		frame.add(fu);
		amb.setBounds(0, 600, 200, 200);
		amb.setIcon(new ImageIcon("amb.png"));
		amb.setVisible(true);
		amb.addActionListener(this);
		frame.add(amb);
		gc.setBounds(0, 800, 200, 200);
		gc.setIcon(new ImageIcon("gc.png"));
		gc.setVisible(true);
		gc.addActionListener(this);
		frame.add(gc);
		next.setBounds(1200, 0, 200, 200);
		next.setIcon(new ImageIcon("next.png"));
		next.setVisible(true);
		next.addActionListener(this);
		frame.add(next);
		next5.setBounds(1400, 0, 200, 100);
		next5.setIcon(new ImageIcon("next5.png"));
		next5.setVisible(true);
		frame.add(next5);
		cur.setBounds(1200, 200, 200, 100);
		cur.setEditable(false);
		frame.add(cur);
		cas.setBounds(1400, 200, 200, 100);
		cas.setEditable(false);
		frame.add(cas);
		ad.setBounds(1200, 300, 400, 200);
		frame.add(ad);

		// grid=new JButton[10][10];
		for (int y = 0; y < 10; y++) {
			for (int x = 0; x < 10; x++) {
				grid[x][y] = new JButton("");
				grid[x][y].setBackground(Color.LIGHT_GRAY);
				panell.add(grid[x][y]);
			}
		}
		// //////GRID
		frame.add(panell, BorderLayout.CENTER);
		frame.setVisible(true);
	}

	public static void main(String[] args) throws Exception {
		new ButtonGrid();
	}

	@Override
	public void actionPerformed(ActionEvent e) { // actionperformed
		cur.setText(Integer.toString(x.getEngine().getCurrentCycle()));
		String y = "";
		for (int z = 0; z < x.getVisibleCitizens().size(); z++) {
			if (x.getVisibleCitizens().get(z).getDisaster().isActive()) {
				ad.setText(y += x.getVisibleCitizens().get(z).getDisaster()
						.toString());
			}
		}
		for (int m = 0; m < x.getVisibleBuildings().size(); m++) {
			if (x.getVisibleBuildings().get(m).getDisaster().isActive()) {
				ad.setText(y += x.getVisibleBuildings().get(m).getDisaster()
						.toString());
			}
		}

		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				if (e.getSource() == grid[i][j]) {
					for (int m = 0; m < x.getVisibleBuildings().size(); m++) {

						if (x.getVisibleBuildings().get(m).getLocation().getX() == i
								&& x.getVisibleBuildings().get(m).getLocation()
										.getY() == j) {
							flagbuilding = true;
							Info.setText(x.getVisibleBuildings().get(m)
									.toString());
						}
					}
					for (int z = 0; z < x.getVisibleCitizens().size(); z++) {

						if (x.getVisibleCitizens().get(z).getLocation().getX() == i
								&& x.getVisibleCitizens().get(z).getLocation()
										.getY() == j) {
							flagcitizen = true;
							Info.setText(x.getVisibleCitizens().get(z)
									.toString());

						}
					}
					for (int k = 0; k < x.getEmergencyUnits().size(); k++) {

						if (x.getEmergencyUnits().get(k).getLocation().getX() == i
								&& x.getEmergencyUnits().get(k).getLocation()
										.getY() == j) {
							Info.setText(x.getEmergencyUnits().get(k)
									.toString());

						}
					}

				}
			}
		}

		if (e.getSource().equals(next)) {

			try {
				x.getEngine().nextCycle();

				cas.setText(Integer.toString(x.getEngine()
						.calculateCasualties()));
				for (int w = 0; w < x.getVisibleBuildings().size(); w++) {
					grid[x.getVisibleBuildings().get(w).getLocation().getX()][x
							.getVisibleBuildings().get(w).getLocation().getY()]
							.setBackground(Color.BLUE);
					grid[x.getVisibleBuildings().get(w).getLocation().getX()][x
							.getVisibleBuildings().get(w).getLocation().getY()]
							.addActionListener(this);

				}
				for (int j = 0; j < x.getVisibleCitizens().size(); j++) {
					grid[x.getVisibleCitizens().get(j).getLocation().getX()][x
							.getVisibleCitizens().get(j).getLocation().getY()]
							.setBackground(Color.black);
					;
					grid[x.getVisibleCitizens().get(j).getLocation().getX()][x
							.getVisibleCitizens().get(j).getLocation().getY()]
							.addActionListener(this);

				}
				for (int s = 0; s < x.getEmergencyUnits().size(); s++) {
					grid[x.getEmergencyUnits().get(s).getLocation().getX()][x
							.getEmergencyUnits().get(s).getLocation().getY()]
							.setBackground(Color.red);
					;
					grid[x.getEmergencyUnits().get(s).getLocation().getX()][x
							.getEmergencyUnits().get(s).getLocation().getY()]
							.addActionListener(this);

				}
			} catch (IncompatibleTargetException e1) {
				// TODO Auto-generated catch block
				JOptionPane
						.showMessageDialog(
								frame,
								
								"The targetted rescuable is incompatible with the unit selected.","Incompatible Target",
								JOptionPane.PLAIN_MESSAGE);
				e1.printStackTrace();
			} catch (CitizenAlreadyDeadException e1) {
				JOptionPane.showMessageDialog(frame, 
						"The citizen has already died.","Citizen is Dead",
						JOptionPane.PLAIN_MESSAGE); // TODO Auto-generated catch
													// block
				e1.printStackTrace();
			} catch (CannotTreatException e1) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(frame, 
						"The unit cannot treat the rescuable selected.","Cannot Treat Rescuable",
						JOptionPane.PLAIN_MESSAGE);
				e1.printStackTrace();
			} catch (BuildingAlreadyCollapsedException e1) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(frame, 
						"The building has already collapsed.","Collapsed Building",
						JOptionPane.PLAIN_MESSAGE);
				e1.printStackTrace();
			}
		}
		if (e.getSource().equals(next5)) {
			skip5();
			cas.setText(Integer.toString(x.getEngine().calculateCasualties()));
			for (int w = 0; w < x.getVisibleBuildings().size(); w++) {
				grid[x.getVisibleBuildings().get(w).getLocation().getX()][x
						.getVisibleBuildings().get(w).getLocation().getY()]
						.setBackground(Color.BLUE);
				grid[x.getVisibleBuildings().get(w).getLocation().getX()][x
						.getVisibleBuildings().get(w).getLocation().getY()]
						.addActionListener(this);

			}
			for (int j = 0; j < x.getVisibleCitizens().size(); j++) {
				grid[x.getVisibleCitizens().get(j).getLocation().getX()][x
						.getVisibleCitizens().get(j).getLocation().getY()]
						.setBackground(Color.black);
				;
				grid[x.getVisibleCitizens().get(j).getLocation().getX()][x
						.getVisibleCitizens().get(j).getLocation().getY()]
						.addActionListener(this);

			}
			for (int s = 0; s < x.getEmergencyUnits().size(); s++) {
				grid[x.getEmergencyUnits().get(s).getLocation().getX()][x
						.getEmergencyUnits().get(s).getLocation().getY()]
						.setBackground(Color.red);
				;
				grid[x.getEmergencyUnits().get(s).getLocation().getX()][x
						.getEmergencyUnits().get(s).getLocation().getY()]
						.addActionListener(this);

			}

		}

		if (e.getSource().equals(amb)) { // 1

			for (int i = 0; i < x.getEmergencyUnits().size(); i++) {

				if (x.getEmergencyUnits().get(i) instanceof Ambulance) {

					for (int p = 0; p < 10; p++) {
						for (int j = 0; j < 10; j++) {
							if (flagcitizen) {
								grid[p][j].addActionListener(this);
								System.out.println(p);

								for (int m = 0; m < x.getVisibleBuildings()
										.size(); m++) {

									if (x.getVisibleBuildings().get(m)
											.getLocation().getX() == p
											&& x.getVisibleBuildings().get(m)
													.getLocation().getY() == j) {

										try {
											x.getEmergencyUnits()
													.get(i)
													.respond(
															x.getVisibleBuildings()
																	.get(m));
											grid[x.getVisibleBuildings().get(m)
													.getLocation().getX()][x
													.getVisibleBuildings()
													.get(m).getLocation()
													.getY()]
													.addActionListener(this);
										} catch (IncompatibleTargetException e1) {
											// TODO Auto-generated catch block
											JOptionPane
											.showMessageDialog(
													frame,
													
													"The targetted rescuable is incompatible with the unit selected.","Incompatible Target",
													JOptionPane.PLAIN_MESSAGE);
											e1.printStackTrace();
										} catch (CannotTreatException e1) {
											// TODO Auto-generated catch block
											JOptionPane.showMessageDialog(frame, 
													"The unit cannot treat the rescuable selected.","Cannot Treat Rescuable",
													JOptionPane.PLAIN_MESSAGE);
											e1.printStackTrace();
										}
									}
								}
								for (int z = 0; z < x.getVisibleCitizens()
										.size(); z++) {
									// System.out.println(p);

									if (flagcitizen
											&& (x.getVisibleBuildings().get(z)
													.getDisaster().isActive())) {
										flagcitizen = false;

										try {
											x.getEmergencyUnits()
													.get(i)
													.respond(
															x.getVisibleCitizens()
																	.get(z));
											grid[x.getVisibleCitizens().get(z)
													.getLocation().getX()][x
													.getVisibleCitizens()
													.get(z).getLocation()
													.getY()]
													.addActionListener(this);
										} catch (IncompatibleTargetException e1) {
											// TODO Auto-generated catch block
											JOptionPane
											.showMessageDialog(
													frame,
													
													"The targetted rescuable is incompatible with the unit selected.","Incompatible Target",
													JOptionPane.PLAIN_MESSAGE);											e1.printStackTrace();
										} catch (CannotTreatException e1) {
											// TODO Auto-generated catch block
											JOptionPane.showMessageDialog(frame, 
													"The unit cannot treat the rescuable selected.","Cannot Treat Rescuable",
													JOptionPane.PLAIN_MESSAGE);
											e1.printStackTrace();
										}
									}
								}

							}

						}
					}

					break;
				}
			}
		}// 1
		if (e.getSource().equals(fu)) { // 1

			for (int i = 0; i < x.getEmergencyUnits().size(); i++) {

				if (x.getEmergencyUnits().get(i) instanceof FireTruck) {

					for (int p = 0; p < 10; p++) {
						for (int j = 0; j < 10; j++) {
							if (flagbuilding) {
								grid[p][j].addActionListener(this);
								System.out.println(p);

								for (int m = 0; m < x.getVisibleBuildings()
										.size(); m++) {

									if (flagbuilding
											&& (x.getVisibleBuildings().get(m)
													.getDisaster().isActive())) {
										flagbuilding = false;
										try { // for zizo the problem hena en
												// the for loop we are in which
												// runs with m targets the first
												// available building in the
												// visible buildings array so
												// whenever you do press on a
												// visible building the flag
												// turns to true, and then the
												// unit targets the first
												// available building.
											System.out.println(x
													.getVisibleBuildings()
													.size()
													+ " size");
											x.getEmergencyUnits()
													.get(i)
													.respond(
															x.getVisibleBuildings()
																	.get(m));
											grid[x.getVisibleBuildings().get(m)
													.getLocation().getX()][x
													.getVisibleBuildings()
													.get(m).getLocation()
													.getY()]
													.addActionListener(this);
										} catch (IncompatibleTargetException e1) {
											// TODO Auto-generated catch block
											JOptionPane
											.showMessageDialog(
													frame,
													
													"The targetted rescuable is incompatible with the unit selected.","Incompatible Target",
													JOptionPane.PLAIN_MESSAGE);
											e1.printStackTrace();
										} catch (CannotTreatException e1) {
											// TODO Auto-generated catch block
											JOptionPane.showMessageDialog(frame, 
													"The unit cannot treat the rescuable selected.","Cannot Treat Rescuable",
													JOptionPane.PLAIN_MESSAGE);
											e1.printStackTrace();
										}
									}
								}
								for (int z = 0; z < x.getVisibleCitizens()
										.size(); z++) {
									// System.out.println(p);

									if (flagcitizen) {

										try {
											x.getEmergencyUnits()
													.get(i)
													.respond(
															x.getVisibleCitizens()
																	.get(z));
											grid[x.getVisibleCitizens().get(z)
													.getLocation().getX()][x
													.getVisibleCitizens()
													.get(z).getLocation()
													.getY()]
													.addActionListener(this);
										} catch (IncompatibleTargetException e1) {
											// TODO Auto-generated catch block
											JOptionPane
											.showMessageDialog(
													frame,
													"The targetted rescuable is incompatible with the unit selected.","Incompatible Target",
													JOptionPane.PLAIN_MESSAGE);
											e1.printStackTrace();
										} catch (CannotTreatException e1) {
											// TODO Auto-generated catch block
											JOptionPane.showMessageDialog(frame, 
													"The unit cannot treat the rescuable selected.","Cannot Treat Rescuable",
													JOptionPane.PLAIN_MESSAGE);
											e1.printStackTrace();
										}
									}
								}

							}

						}
					}

					break;
				}
			}
		}// 1
		if (e.getSource().equals(evac)) { // 1

			for (int i = 0; i < x.getEmergencyUnits().size(); i++) {

				if (x.getEmergencyUnits().get(i) instanceof Evacuator) {

					for (int p = 0; p < 10; p++) {
						for (int j = 0; j < 10; j++) {
							if (flagbuilding) {
								grid[p][j].addActionListener(this);
								System.out.println(p);

								for (int m = 0; m < x.getVisibleBuildings()
										.size(); m++) {

									if (flagbuilding
											&& (x.getVisibleBuildings().get(m)
													.getDisaster().isActive())) {
										flagbuilding = false;
										try {
											x.getEmergencyUnits()
													.get(i)
													.respond(
															x.getVisibleBuildings()
																	.get(m));
											grid[x.getVisibleBuildings().get(m)
													.getLocation().getX()][x
													.getVisibleBuildings()
													.get(m).getLocation()
													.getY()]
													.addActionListener(this);
										} catch (IncompatibleTargetException e1) {
											// TODO Auto-generated catch block
											JOptionPane
											.showMessageDialog(
													frame,
													
													"The targetted rescuable is incompatible with the unit selected.","Incompatible Target",
													JOptionPane.PLAIN_MESSAGE);
											e1.printStackTrace();
										} catch (CannotTreatException e1) {
											// TODO Auto-generated catch block
											JOptionPane.showMessageDialog(frame, 
													"The unit cannot treat the rescuable selected.","Cannot Treat Rescuable",
													JOptionPane.PLAIN_MESSAGE);
											e1.printStackTrace();
										}
									}
								}
								for (int z = 0; z < x.getVisibleCitizens()
										.size(); z++) {
									// System.out.println(p);

									if (flagcitizen
											&& (x.getVisibleBuildings().get(z)
													.getDisaster().isActive())) {
										flagcitizen = false;

										try {
											x.getEmergencyUnits()
													.get(i)
													.respond(
															x.getVisibleCitizens()
																	.get(z));
											grid[x.getVisibleCitizens().get(z)
													.getLocation().getX()][x
													.getVisibleCitizens()
													.get(z).getLocation()
													.getY()]
													.addActionListener(this);
										} catch (IncompatibleTargetException e1) {
											// TODO Auto-generated catch block
											JOptionPane
											.showMessageDialog(
													frame,
													
													"The targetted rescuable is incompatible with the unit selected.","Incompatible Target",
													JOptionPane.PLAIN_MESSAGE);
											e1.printStackTrace();
										} catch (CannotTreatException e1) {
											// TODO Auto-generated catch block
											JOptionPane.showMessageDialog(frame, 
													"The unit cannot treat the rescuable selected.","Cannot Treat Rescuable",
													JOptionPane.PLAIN_MESSAGE);
											e1.printStackTrace();
										}
									}
								}

							}

						}
					}

					break;
				}
			}
		}// 1
		if (e.getSource().equals(dc)) { // 1

			for (int i = 0; i < x.getEmergencyUnits().size(); i++) {

				if (x.getEmergencyUnits().get(i) instanceof DiseaseControlUnit) {

					for (int p = 0; p < 10; p++) {
						for (int j = 0; j < 10; j++) {
							if (flagcitizen) {
								grid[p][j].addActionListener(this);
								System.out.println(p);

								for (int m = 0; m < x.getVisibleBuildings()
										.size(); m++) {

									if (flagbuilding) {

										try {
											x.getEmergencyUnits()
													.get(i)
													.respond(
															x.getVisibleBuildings()
																	.get(m));
											grid[x.getVisibleBuildings().get(m)
													.getLocation().getX()][x
													.getVisibleBuildings()
													.get(m).getLocation()
													.getY()]
													.addActionListener(this);
										} catch (IncompatibleTargetException e1) {
											// TODO Auto-generated catch block
											JOptionPane
											.showMessageDialog(
													frame,
													
													"The targetted rescuable is incompatible with the unit selected.","Incompatible Target",
													JOptionPane.PLAIN_MESSAGE);
											e1.printStackTrace();
										} catch (CannotTreatException e1) {
											// TODO Auto-generated catch block
											JOptionPane.showMessageDialog(frame, 
													"The unit cannot treat the rescuable selected.","Cannot Treat Rescuable",
													JOptionPane.PLAIN_MESSAGE);
											e1.printStackTrace();
										}
									}
								}
								for (int z = 0; z < x.getVisibleCitizens()
										.size(); z++) {
									// System.out.println(p);

									if (flagcitizen
											&& (x.getVisibleBuildings().get(z)
													.getDisaster().isActive())) {
										flagcitizen = false;

										try {
											x.getEmergencyUnits()
													.get(i)
													.respond(
															x.getVisibleCitizens()
																	.get(z));
											grid[x.getVisibleCitizens().get(z)
													.getLocation().getX()][x
													.getVisibleCitizens()
													.get(z).getLocation()
													.getY()]
													.addActionListener(this);
										} catch (IncompatibleTargetException e1) {
											// TODO Auto-generated catch block
											JOptionPane
											.showMessageDialog(
													frame,
													
													"The targetted rescuable is incompatible with the unit selected.","Incompatible Target",
													JOptionPane.PLAIN_MESSAGE);
											e1.printStackTrace();
										} catch (CannotTreatException e1) {
											// TODO Auto-generated catch block
											JOptionPane.showMessageDialog(frame, 
													"The unit cannot treat the rescuable selected.","Cannot Treat Rescuable",
													JOptionPane.PLAIN_MESSAGE);
											e1.printStackTrace();
										}
									}
								}

							}

						}
					}

					break;
				}
			}
		}// 1
		if (e.getSource().equals(gc)) { // 1

			for (int i = 0; i < x.getEmergencyUnits().size(); i++) {

				if (x.getEmergencyUnits().get(i) instanceof GasControlUnit) {

					for (int p = 0; p < 10; p++) {
						for (int j = 0; j < 10; j++) {
							if (flagbuilding) {
								grid[p][j].addActionListener(this);
								System.out.println(p);

								for (int m = 0; m < x.getVisibleBuildings()
										.size(); m++) {

									if (flagbuilding
											&& (x.getVisibleBuildings().get(m)
													.getDisaster().isActive()) && x.getVisibleBuildings().get(m).getDisaster() instanceof GasLeak ) {
										flagbuilding = false;
										try {
											x.getEmergencyUnits()
													.get(i)
													.respond(
															x.getVisibleBuildings()
																	.get(m));
											grid[x.getVisibleBuildings().get(m)
													.getLocation().getX()][x
													.getVisibleBuildings()
													.get(2).getLocation()
													.getY()]
													.addActionListener(this);
										} catch (IncompatibleTargetException e1) {
											// TODO Auto-generated catch block
											JOptionPane
											.showMessageDialog(
													frame,
													
													"The targetted rescuable is incompatible with the unit selected.","Incompatible Target",
													JOptionPane.PLAIN_MESSAGE);
											e1.printStackTrace();
										} catch (CannotTreatException e1) {
											// TODO Auto-generated catch block
											JOptionPane.showMessageDialog(frame, 
													"The unit cannot treat the rescuable selected.","Cannot Treat Rescuable",
													JOptionPane.PLAIN_MESSAGE);
											e1.printStackTrace();
										}
									}
								}
								for (int z = 0; z < x.getVisibleCitizens()
										.size(); z++) {
									// System.out.println(p);

									if (flagcitizen) {
										flagbuilding = false;

										try {
											x.getEmergencyUnits()
													.get(i)
													.respond(
															x.getVisibleCitizens()
																	.get(z));
											grid[x.getVisibleCitizens().get(z)
													.getLocation().getX()][x
													.getVisibleCitizens()
													.get(z).getLocation()
													.getY()]
													.addActionListener(this);
										} catch (IncompatibleTargetException e1) {
											// TODO Auto-generated catch block
											JOptionPane
											.showMessageDialog(
													frame,
													"Incompatible Target",
													"The targetted rescuable is incompatible with the unit selected.",
													JOptionPane.PLAIN_MESSAGE);
											e1.printStackTrace();
										} catch (CannotTreatException e1) {
											// TODO Auto-generated catch block
											JOptionPane.showMessageDialog(frame, "Cannot Treat Rescuable",
													"The unit cannot treat the rescuable selected.",
													JOptionPane.PLAIN_MESSAGE);
											e1.printStackTrace();
										}
									}
								}

							}

						}
					}

					break;
				}
			}
		}// 1
	} // actionperformed

	public void skip5() {
		for (int i = 0; i < 5; i++) {
			try {
				x.getEngine().nextCycle();
			} catch (IncompatibleTargetException e1) {
				// TODO Auto-generated catch block
				JOptionPane
				.showMessageDialog(
						frame,
						
						"The targetted rescuable is incompatible with the unit selected.","Incompatible Target",
						JOptionPane.PLAIN_MESSAGE);
				e1.printStackTrace();
			} catch (CitizenAlreadyDeadException e1) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(frame, 
						"The citizen has already died.","Citizen is Dead",
						JOptionPane.PLAIN_MESSAGE);
				e1.printStackTrace();
			} catch (CannotTreatException e1) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(frame, 
						"The unit cannot treat the rescuable selected.","Cannot Treat Rescuable",
						JOptionPane.PLAIN_MESSAGE);
				e1.printStackTrace();
			} catch (BuildingAlreadyCollapsedException e1) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(frame, "The building has already collapsed.","Collapsed Building",
						JOptionPane.PLAIN_MESSAGE);
				e1.printStackTrace();
			}
		}
	}
}
