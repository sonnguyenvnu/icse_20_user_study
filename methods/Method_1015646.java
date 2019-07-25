public String percentiles(){
  if (values == null)   return "n/a";
  Collections.sort(values);
  double stddev=stddev();
  return String.format("stddev: %.2f, 50: %d, 90: %d, 99: %d, 99.9: %d, 99.99: %d, 99.999: %d, 100: %d\n",stddev,p(50),p(90),p(99),p(99.9),p(99.99),p(99.999),p(100));
}
