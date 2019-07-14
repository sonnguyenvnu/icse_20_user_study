/** 
 * Gets the number of pairs of methods that use at least one attribute in common.
 * @param usagesByMethod Map of method name to names of local attributes accessed
 * @return The number of pairs
 */
private int numMethodsRelatedByAttributeAccess(Map<String,Set<String>> usagesByMethod){
  List<String> methods=new ArrayList<>(usagesByMethod.keySet());
  int methodCount=methods.size();
  int pairs=0;
  if (methodCount > 1) {
    for (int i=0; i < methodCount - 1; i++) {
      for (int j=i + 1; j < methodCount; j++) {
        String firstMethodName=methods.get(i);
        String secondMethodName=methods.get(j);
        if (!Collections.disjoint(usagesByMethod.get(firstMethodName),usagesByMethod.get(secondMethodName))) {
          pairs++;
        }
      }
    }
  }
  return pairs;
}
