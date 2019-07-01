/** 
 * Calculate the number of objects to test in a run of the idle object evictor.
 * @return The number of objects to test for validity
 */
private int _XXXXX_(){
  final int totalIdle=getNumIdle();
  final int numTests=getNumTestsPerEvictionRun();
  if (numTests >= 0) {
    return Math.min(numTests,totalIdle);
  }
  return (int)(Math.ceil(totalIdle / Math.abs((double)numTests)));
}