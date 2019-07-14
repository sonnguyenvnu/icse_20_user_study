private static int nsToSamples(long ns){
  return (int)(ns * SAMPLE_RATE / 1000000000);
}
