void stamp(){
  double l1=inductance;
  double l2=inductance * ratio * ratio;
  double m=couplingCoef * Math.sqrt(l1 * l2);
  double deti=1 / (l1 * l2 - m * m);
  double ts=isTrapezoidal() ? sim.timeStep / 2 : sim.timeStep;
  a1=l2 * deti * ts;
  a2=-m * deti * ts;
  a3=-m * deti * ts;
  a4=l1 * deti * ts;
  sim.stampConductance(nodes[0],nodes[2],a1);
  sim.stampVCCurrentSource(nodes[0],nodes[2],nodes[1],nodes[3],a2);
  sim.stampVCCurrentSource(nodes[1],nodes[3],nodes[0],nodes[2],a3);
  sim.stampConductance(nodes[1],nodes[3],a4);
  sim.stampRightSide(nodes[0]);
  sim.stampRightSide(nodes[1]);
  sim.stampRightSide(nodes[2]);
  sim.stampRightSide(nodes[3]);
}
