/** 
 * test if the intersection of two sets is not empty
 * @param set1
 * @param set2
 * @return true if any element of the first set is part of the second set or vice-versa
 */
public static boolean anymatch(final HandleSet set1,final HandleSet set2){
  if ((set1 == null) || (set2 == null))   return false;
  if (set1.comparator() != set2.comparator())   return false;
  if (set1.isEmpty() || set2.isEmpty())   return false;
  final int high=((set1.size() > set2.size()) ? set1.size() : set2.size());
  final int low=((set1.size() > set2.size()) ? set2.size() : set1.size());
  final int stepsEnum=10 * (high + low - 1);
  final int stepsTest=12 * log2a(high) * low;
  if (stepsEnum > stepsTest) {
    if (set1.size() < set2.size())     return anymatchByTest(set1,set2);
    return anymatchByTest(set2,set1);
  }
  return anymatchByEnumeration(set1,set2);
}
