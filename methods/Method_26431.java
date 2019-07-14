/** 
 * Converts "MS"/"MSec"/etc. to "Ms"/etc. and "microSeconds"/"MicroSeconds"/etc. to "microseconds"/"Microseconds"/etc.
 */
private static ImmutableList<String> fixUnitCamelCase(List<String> words){
  ImmutableList.Builder<String> out=ImmutableList.builderWithExpectedSize(words.size());
  int i=0;
  for (i=0; i < words.size() - 1; i++) {
    String current=words.get(i);
    String next=words.get(i + 1);
    String together=current + next;
    if (UNIT_FOR_SUFFIX.containsKey(together)) {
      out.add(together);
      i++;
    }
 else {
      out.add(current);
    }
  }
  if (i == words.size() - 1) {
    out.add(words.get(i));
  }
  return out.build();
}
