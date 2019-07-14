private static boolean isBufferVeryLate(long earlyUs){
  return earlyUs < -500000;
}
