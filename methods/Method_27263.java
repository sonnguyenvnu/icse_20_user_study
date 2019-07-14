private static List<String> jsonArrayToStringList(JSONArray array){
  List<String> strings=new ArrayList<String>(array.length());
  try {
    for (int i=0; i < array.length(); i++) {
      strings.add(array.getString(i));
    }
  }
 catch (  Exception e) {
    e.printStackTrace();
  }
  return strings;
}
