void execute(){
  if (oneshot) {
    if (sim.t - lastchangetime > 0.005) {
      if (position <= 8)       GetNextBit();
      lastchangetime=sim.t;
    }
  }
  if (pins[0].value && !clockstate) {
    clockstate=true;
    if (oneshot) {
      position=0;
    }
 else {
      GetNextBit();
      if (position >= 8)       position=0;
    }
  }
  if (!pins[0].value)   clockstate=false;
}
