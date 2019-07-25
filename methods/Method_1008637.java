@Override public Iterator<Percentile> iterator(){
  return new Iterator<Percentile>(){
    @Override public boolean hasNext(){
      return iterator.hasNext();
    }
    @Override public Percentile next(){
      Map.Entry<Double,Double> next=iterator.next();
      return new Percentile(next.getKey(),next.getValue());
    }
  }
;
}
