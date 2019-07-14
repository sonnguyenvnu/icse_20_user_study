private static boolean isBufferLate(long earlyUs){
  return earlyUs < -30000;
}
