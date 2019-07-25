void stamp(){
  sim.stampNonLinear(nodes[1]);
  sim.stampNonLinear(nodes[2]);
  if (doBodyDiode()) {
    if (pnp == -1) {
      diodeB1.stamp(nodes[1],nodes[bodyTerminal]);
      diodeB2.stamp(nodes[2],nodes[bodyTerminal]);
    }
 else {
      diodeB1.stamp(nodes[bodyTerminal],nodes[1]);
      diodeB2.stamp(nodes[bodyTerminal],nodes[2]);
    }
  }
}
