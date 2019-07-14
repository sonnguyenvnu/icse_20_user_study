@Override protected String assemble(List<PolicyStats> results){
  StringWriter output=new StringWriter();
  CsvWriter writer=new CsvWriter(output,new CsvWriterSettings());
  writer.writeHeaders(headers());
  for (  PolicyStats policyStats : results) {
    Object[] data={policyStats.name(),String.format("%.2f",100 * policyStats.hitRate()),policyStats.hitCount(),policyStats.missCount(),policyStats.requestCount(),policyStats.evictionCount(),String.format("%.2f",100 * policyStats.admissionRate()),(policyStats.operationCount() == 0) ? null : policyStats.operationCount(),policyStats.stopwatch().elapsed(TimeUnit.MILLISECONDS)};
    writer.writeRow(data);
  }
  writer.close();
  return output.toString();
}
