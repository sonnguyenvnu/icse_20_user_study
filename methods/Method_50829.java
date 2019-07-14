@Override public void generate(Set<RuleDuration> stressResults,PrintStream stream){
  stream.println("=========================================================");
  stream.println("Rule\t\t\t\t\t\tTime in ms");
  stream.println("=========================================================");
  for (  RuleDuration result : stressResults) {
    StringBuilder buffer=new StringBuilder(result.rule.getName());
    while (buffer.length() < TIME_COLUMN) {
      buffer.append(' ');
    }
    buffer.append(result.time);
    stream.println(stream.toString());
  }
  stream.println("=========================================================");
}
