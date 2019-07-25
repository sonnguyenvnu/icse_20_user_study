protected double stddev(){
  if (values == null)   return -1.0;
  double av=average();
  int size=values.size();
  double variance=values.stream().map(v -> (v - av) * (v - av)).reduce(0.0,(x,y) -> x + y) / size;
  return Math.sqrt(variance);
}
