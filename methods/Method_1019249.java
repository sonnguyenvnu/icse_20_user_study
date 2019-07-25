@Benchmark void contains(int reps){
  for (int i=0; i < reps; i++) {
    for (    Object query : queries) {
      set.contains(query);
    }
  }
}
