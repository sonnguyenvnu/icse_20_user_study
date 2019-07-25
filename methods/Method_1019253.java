@Benchmark void time(int reps) throws Exception {
  for (int i=0; i < reps; i++) {
    MessageDigest.getInstance(algorithm);
  }
}
