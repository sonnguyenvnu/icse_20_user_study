@Benchmark int chars(int reps){
  int dummy=0;
  for (int i=0; i < reps; i++) {
    dummy+=System.identityHashCode(strategy.copy(charArray));
  }
  return dummy;
}
