public static String[] toNameAndValues(Map<String,List<String>> nameAndValuesMap){
  return toNameAndValuesSet(nameAndValuesMap).toArray(new String[0]);
}
