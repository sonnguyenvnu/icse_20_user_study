public void compile(){
  int cpt=0;
  final Map<PositiveForce,Integer> counter=new HashMap<PositiveForce,Integer>();
  do {
    boolean done=true;
    for (    PositiveForce f : forces) {
      final boolean change=f.apply();
      if (change) {
        incCounter(counter,f);
        done=false;
      }
    }
    if (done) {
      CPT+=cpt;
      min=0;
      max=0;
      for (      AbstractReal real : all) {
        final double v=real.getCurrentValue();
        if (v > max) {
          max=v;
        }
        if (v < min) {
          min=v;
        }
      }
      return;
    }
    cpt++;
    if (cpt > 99999) {
      printCounter(counter);
      throw new IllegalStateException("Inifinite Loop?");
    }
  }
 while (true);
}
