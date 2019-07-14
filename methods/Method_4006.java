/** 
 * Time base for deadline-aware work scheduling. Overridable for testing. Will return 0 to avoid cost of System.nanoTime where deadline-aware work scheduling isn't relevant.
 */
long getNanoTime(){
  if (ALLOW_THREAD_GAP_WORK) {
    return System.nanoTime();
  }
 else {
    return 0;
  }
}
