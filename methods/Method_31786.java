/** 
 * End the stopwatch and print the result.
 */
private void end(int count){
  end=System.currentTimeMillis();
  long time=(end - start);
  result.time=result.time + time;
  result.runs=result.runs + count;
  result.avg=(result.time * 1000000) / result.runs;
  System.out.print(".");
}
