void repaint(){
  if (!needsRepaint) {
    needsRepaint=true;
    Scheduler.get().scheduleFixedDelay(new Scheduler.RepeatingCommand(){
      public boolean execute(){
        updateCircuit();
        needsRepaint=false;
        return false;
      }
    }
,FASTTIMER);
  }
}
