/** 
 * @param timeTotals long[]
 * @param index Benchmark
 * @param count long
 * @return double
 */
private static double total(long[] timeTotals,Benchmark index,long count){
  return timeTotals[index.index] / 1000000000.0d / count;
}
