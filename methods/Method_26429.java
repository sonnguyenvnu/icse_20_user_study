/** 
 * Returns true if the input looks like [from, seconds]. 
 */
private static boolean hasNameOfFromUnits(List<String> words){
  return words.size() == 2 && words.get(0).equals("from") && UNIT_FOR_SUFFIX.containsKey(words.get(1));
}
