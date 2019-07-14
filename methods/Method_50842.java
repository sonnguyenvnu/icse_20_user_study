private static boolean isClassPathWildcard(String entry){
  return entry.endsWith("/*") || entry.endsWith("\\*");
}
