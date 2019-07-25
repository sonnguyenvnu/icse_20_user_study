void stamp(){
  if (needsPullDown())   sim.stampResistor(nodes[0],0,1e6);
}
