public static <T extends Enum<?>>T lookup(Map<String,T> lookup,String name,boolean fuzzy){
  String testName=name.replaceAll("[ _]","").toLowerCase(Locale.ROOT);
  T type=lookup.get(testName);
  if (type != null) {
    return type;
  }
  if (!fuzzy) {
    return null;
  }
  int minDist=-1;
  for (  Map.Entry<String,T> entry : lookup.entrySet()) {
    final String key=entry.getKey();
    if (key.charAt(0) != testName.charAt(0)) {
      continue;
    }
    int dist=getLevenshteinDistance(key,testName);
    if ((dist < minDist || minDist == -1) && dist < 2) {
      minDist=dist;
      type=entry.getValue();
    }
  }
  return type;
}
