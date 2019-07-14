/** 
 * Returns true if the input looks like [five, seconds]. 
 */
private static boolean isNamedForNumberOfUnits(List<String> words){
  return words.size() == 2 && NUMBER_WORDS.contains(words.get(0)) && UNIT_FOR_SUFFIX.containsKey(words.get(1));
}
