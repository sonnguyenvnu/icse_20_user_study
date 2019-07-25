private long average(List<Long> times){
  long total=0;
  for (  final long v : times) {
    total+=v;
  }
  return total / times.size();
}
