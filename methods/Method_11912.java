/** 
 * Returns the formatted string of the elapsed time. Duplicated from BaseTestRunner. Fix it.
 */
protected String elapsedTimeAsString(long runTime){
  return NumberFormat.getInstance().format((double)runTime / 1000);
}
