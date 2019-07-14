@Override public Streak getNewestComputed(){
  Streak newest=null;
  for (  Streak s : list)   if (newest == null || s.getEnd().isNewerThan(newest.getEnd()))   newest=s;
  return newest;
}
