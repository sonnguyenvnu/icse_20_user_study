void stamp(){
  int i;
  int add=(hasTriState()) ? outputCount : 0;
  for (i=0; i != getPostCount(); i++) {
    Pin p=pins[i];
    if (p.output) {
      sim.stampVoltageSource(0,nodes[i + add],p.voltSource);
      if (hasTriState()) {
        sim.stampNonLinear(nodes[i + add]);
        sim.stampNonLinear(nodes[i]);
      }
    }
  }
}
