@Override public List<T> next(){
  List<T> result=new ArrayList<>();
  int n=count;
  for (  Buffer<T> each : buffers) {
    int divisor=each.divisor();
    result.add(each.get(n));
    n/=divisor;
  }
  ++count;
  return result;
}
