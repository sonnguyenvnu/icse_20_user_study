@Override public void releasePeriod(MediaPeriod mediaPeriod){
  DashMediaPeriod dashMediaPeriod=(DashMediaPeriod)mediaPeriod;
  dashMediaPeriod.release();
  periodsById.remove(dashMediaPeriod.id);
}
