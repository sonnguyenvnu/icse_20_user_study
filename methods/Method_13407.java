/** 
 * To the name and value line sets
 * @param nameAndValuesMap {@link MultiValueMap} the map of name and values
 * @return non-null
 */
public static Set<String> toNameAndValuesSet(Map<String,List<String>> nameAndValuesMap){
  Set<String> nameAndValues=new LinkedHashSet<>();
  for (  Map.Entry<String,List<String>> entry : nameAndValuesMap.entrySet()) {
    String name=entry.getKey();
    List<String> values=entry.getValue();
    for (    String value : values) {
      String nameAndValue=name + EQUAL + value;
      nameAndValues.add(nameAndValue);
    }
  }
  return nameAndValues;
}
