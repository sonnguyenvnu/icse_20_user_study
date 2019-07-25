static LzStore optimal(Cookie cookie,int numIterations,LongestMatchCache lmc,byte[] input,int from,int to){
  LzStore currentStore=cookie.store1;
  currentStore.reset();
  LzStore store=cookie.store2;
  Deflate.greedy(cookie,lmc,input,from,to,currentStore);
  SymbolStats stats=cookie.stats;
  SymbolStats bestStats=cookie.bestStats;
  SymbolStats lastStats=cookie.lastStats;
  stats.getFreqs(currentStore);
  char[] lengthArray=cookie.lengthArray;
  long[] costs=cookie.costs;
  int cost;
  int bestCost=Integer.MAX_VALUE;
  int lastCost=0;
  int lastRandomStep=-1;
  for (int i=0; i < numIterations; i++) {
    currentStore.reset();
    bestLengths(cookie,lmc,from,input,from,to,stats.minCost(),stats,lengthArray,costs);
    optimalRun(cookie,lmc,input,from,to,lengthArray,currentStore);
    cost=Deflate.calculateBlockSize(cookie,currentStore.litLens,currentStore.dists,0,currentStore.size);
    if (cost < bestCost) {
      store.copy(currentStore);
      bestStats.copy(stats);
      bestCost=cost;
    }
    lastStats.copy(stats);
    stats.getFreqs(currentStore);
    if (lastRandomStep != -1) {
      stats.alloy(lastStats);
      stats.calculate();
    }
    if (i > 5 && cost == lastCost) {
      stats.copy(bestStats);
      cookie.rnd=stats.randomizeFreqs(cookie.rnd);
      stats.calculate();
      lastRandomStep=i;
    }
    lastCost=cost;
  }
  return store;
}
