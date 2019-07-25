private static int staircase(int n){
  if (n < 0) {
    return 0;
  }
  if (cache.containsKey(n)) {
    return cache.get(n);
  }
  int ways=staircase(n - 1) + staircase(n - 2) + staircase(n - 3);
  cache.put(n,ways);
  return ways;
}
