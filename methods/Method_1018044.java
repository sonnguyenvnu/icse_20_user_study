@Override public boolean accepts(Metric metric){
  return metric instanceof WithinSchedule;
}
