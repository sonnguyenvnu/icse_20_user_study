@Benchmark int primitive(int reps){
  double d=value.value;
  int dummy=0;
  for (int i=0; i < reps; i++) {
    dummy+=method.convert(d).length();
  }
  return dummy;
}
