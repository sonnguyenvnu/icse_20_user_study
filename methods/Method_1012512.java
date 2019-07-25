void stamp(){
  ind.stamp(nodes[nCoil1],nodes[nCoil3]);
  sim.stampResistor(nodes[nCoil3],nodes[nCoil2],coilR);
  int i;
  for (i=0; i != poleCount * 3; i++)   sim.stampNonLinear(nodes[nSwitch0 + i]);
}
