private static List<String> createLocalitiesWithoutCode(){
  List<String> allLocalities=new ArrayList<>();
  for (  String localityWithCode : LOCALITIES) {
    allLocalities.add(localityWithCode.substring(10));
  }
  Collections.sort(allLocalities);
  return allLocalities;
}
