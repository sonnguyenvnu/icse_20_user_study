private void compare(String label,Cache<Integer,Integer> caffeine,com.google.common.cache.Cache<Integer,Integer> guava){
  caffeine.cleanUp();
  guava.cleanUp();
  int leftPadded=Math.max((36 - label.length()) / 2 - 1,1);
  out.printf(" %2$-" + leftPadded + "s %s%n",label," ");
  String result=FlipTable.of(new String[]{"Cache","Baseline","Per Entry"},new String[][]{evaluate("Caffeine",caffeine.asMap()),evaluate("Guava",guava.asMap())});
  out.println(result);
}
