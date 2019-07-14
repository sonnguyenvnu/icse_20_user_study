/** 
 * Compiles a list of regex into a list of patterns.
 * @param list the regex list
 * @return the pattern list
 */
public static List<Pattern> compilePatternsFromList(List<String> list){
  List<Pattern> patterns;
  if (list != null && !list.isEmpty()) {
    patterns=new ArrayList<>(list.size());
    for (    String stringPattern : list) {
      if (stringPattern != null && !"".equals(stringPattern)) {
        patterns.add(Pattern.compile(stringPattern));
      }
    }
  }
 else {
    patterns=new ArrayList<>(0);
  }
  return patterns;
}
