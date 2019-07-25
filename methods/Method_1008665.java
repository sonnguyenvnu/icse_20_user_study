@Override public <T extends Number>double next(Collection<T> values){
  double avg=0;
  boolean first=true;
  for (  T v : values) {
    if (first) {
      avg=v.doubleValue();
      first=false;
    }
 else {
      avg=(v.doubleValue() * alpha) + (avg * (1 - alpha));
    }
  }
  return avg;
}
