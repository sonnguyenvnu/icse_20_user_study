@Override public <T extends Number>double next(Collection<T> values){
  double avg=0;
  long totalWeight=1;
  long current=1;
  for (  T v : values) {
    avg+=v.doubleValue() * current;
    totalWeight+=current;
    current+=1;
  }
  return avg / totalWeight;
}
