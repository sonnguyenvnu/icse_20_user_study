/** 
 * Assembles an aggregated report. 
 */
@Override @SuppressWarnings("PMD.AvoidDuplicateLiterals") protected String assemble(List<PolicyStats> results){
  String[][] data=new String[results.size()][headers().length];
  for (int i=0; i < results.size(); i++) {
    PolicyStats policyStats=results.get(i);
    data[i]=new String[]{policyStats.name(),String.format("%.2f %%",100 * policyStats.hitRate()),String.format("%,d",policyStats.hitCount()),String.format("%,d",policyStats.missCount()),String.format("%,d",policyStats.requestCount()),String.format("%,d",policyStats.evictionCount()),String.format("%.2f %%",100 * policyStats.admissionRate()),steps(policyStats),policyStats.stopwatch().toString()};
  }
  return FlipTable.of(headers(),data);
}
