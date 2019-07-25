private static double hypot(double... pars){
  double sum=0;
  for (  double d : pars) {
    sum+=Math.pow(d,2);
  }
  return Math.sqrt(sum);
}
