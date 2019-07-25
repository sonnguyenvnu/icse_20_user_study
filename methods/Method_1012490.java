void stamp(int n0,int n1){
  nodes[0]=n0;
  nodes[1]=n1;
  sim.stampNonLinear(nodes[0]);
  sim.stampNonLinear(nodes[1]);
}
