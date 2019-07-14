private void computeNumerical(Repetition[] reps){
  if (reps.length == 0)   throw new IllegalArgumentException();
  Timestamp today=DateUtils.getToday();
  Timestamp begin=reps[0].getTimestamp();
  int nDays=begin.daysUntil(today) + 1;
  List<Checkmark> checkmarks=new ArrayList<>(nDays);
  for (int i=0; i < nDays; i++)   checkmarks.add(new Checkmark(today.minus(i),0));
  for (  Repetition rep : reps) {
    int offset=rep.getTimestamp().daysUntil(today);
    checkmarks.set(offset,new Checkmark(rep.getTimestamp(),rep.getValue()));
  }
  add(checkmarks);
}
