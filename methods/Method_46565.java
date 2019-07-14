public static List<String> parseCSVList(String csv){
  if (csv == null || csv.isEmpty()) {
    return Collections.emptyList();
  }
  return new ArrayList<String>(Arrays.asList(csv.split(",")));
}
