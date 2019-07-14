@Override public Pair<Configuration,Integer> call() throws Exception {
  if (!partial)   return parse();
 else   return new Pair<Configuration,Integer>(parsePartial(),id);
}
