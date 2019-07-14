@Override public void generate(Map<String,BenchmarkResult> benchmarksByName,PrintStream stream){
  List<BenchmarkResult> results=new ArrayList<>(benchmarksByName.values());
  long[] totalTime=new long[Benchmark.TotalPMD.index + 1];
  long[] totalCount=new long[Benchmark.TotalPMD.index + 1];
  for (  BenchmarkResult benchmarkResult : results) {
    totalTime[benchmarkResult.type.index]+=benchmarkResult.getTime();
    totalCount[benchmarkResult.type.index]+=benchmarkResult.getCount();
    if (benchmarkResult.type.index < Benchmark.MeasuredTotal.index) {
      totalTime[Benchmark.MeasuredTotal.index]+=benchmarkResult.getTime();
    }
  }
  results.add(new BenchmarkResult(Benchmark.RuleTotal,totalTime[Benchmark.RuleTotal.index],0));
  results.add(new BenchmarkResult(Benchmark.RuleChainTotal,totalTime[Benchmark.RuleChainTotal.index],0));
  results.add(new BenchmarkResult(Benchmark.MeasuredTotal,totalTime[Benchmark.MeasuredTotal.index],0));
  results.add(new BenchmarkResult(Benchmark.NonMeasuredTotal,totalTime[Benchmark.TotalPMD.index] - totalTime[Benchmark.MeasuredTotal.index],0));
  Collections.sort(results);
  StringBuilderCR buf=new StringBuilderCR(PMD.EOL);
  boolean writeRuleHeader=true;
  boolean writeRuleChainRuleHeader=true;
  long ruleCount=0;
  long ruleChainCount=0;
  for (  BenchmarkResult benchmarkResult : results) {
    StringBuilder buf2=new StringBuilder(benchmarkResult.name);
    buf2.append(':');
    while (buf2.length() <= NAME_COLUMN_WIDTH) {
      buf2.append(' ');
    }
    String result=MessageFormat.format("{0,number,0.000}",Double.valueOf(benchmarkResult.getTime() / 1000000000.0));
    buf2.append(StringUtils.leftPad(result,VALUE_COLUMN_WIDTH));
    if (benchmarkResult.type.index <= Benchmark.RuleChainRule.index) {
      buf2.append(StringUtils.leftPad(MessageFormat.format("{0,number,###,###,###,###,###}",benchmarkResult.getCount()),20));
    }
switch (benchmarkResult.type) {
case Rule:
      if (writeRuleHeader) {
        writeRuleHeader=false;
        buf.appendLn();
        buf.appendLn("---------------------------------<<< Rules >>>---------------------------------");
        buf.appendLn("Rule name                                       Time (secs)    # of Evaluations");
        buf.appendLn();
      }
    ruleCount++;
  break;
case RuleChainRule:
if (writeRuleChainRuleHeader) {
  writeRuleChainRuleHeader=false;
  buf.appendLn();
  buf.appendLn("----------------------------<<< RuleChain Rules >>>----------------------------");
  buf.appendLn("Rule name                                       Time (secs)         # of Visits");
  buf.appendLn();
}
ruleChainCount++;
break;
case CollectFiles:
buf.appendLn();
buf.appendLn("--------------------------------<<< Summary >>>--------------------------------");
buf.appendLn("Segment                                         Time (secs)");
buf.appendLn();
break;
case MeasuredTotal:
String s=MessageFormat.format("{0,number,###,###,###,###,###}",ruleCount);
String t=MessageFormat.format("{0,number,0.000}",ruleCount == 0 ? 0 : total(totalTime,Benchmark.Rule,ruleCount));
buf.appendLn("Rule Average (",s," rules):",StringUtils.leftPad(t,37 - s.length()));
s=MessageFormat.format("{0,number,###,###,###,###,###}",ruleChainCount);
t=MessageFormat.format("{0,number,0.000}",ruleChainCount == 0 ? 0 : total(totalTime,Benchmark.RuleChainRule,ruleChainCount));
buf.appendLn("RuleChain Average (",s," rules):",StringUtils.leftPad(t,32 - s.length()));
buf.appendLn();
buf.appendLn("-----------------------------<<< Final Summary >>>-----------------------------");
buf.appendLn("Total                                           Time (secs)");
buf.appendLn();
break;
default :
break;
}
buf.appendLn(buf2.toString());
}
stream.print(buf.toString());
}
