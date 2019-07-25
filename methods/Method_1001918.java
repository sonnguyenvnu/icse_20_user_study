public double inflate(double v){
  double result=v;
  for (  Inflater in : all) {
    if (v > in.start) {
      result+=in.size;
    }
  }
  return result;
}
