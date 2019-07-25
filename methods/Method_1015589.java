public void destroy(){
  for (  Tuple<Rule,Future<?>> tuple : rules.values())   tuple.getVal2().cancel(true);
  rules.clear();
  super.destroy();
}
