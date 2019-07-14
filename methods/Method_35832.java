public static Map<String,ListOrSingle<String>> parse(String formText,boolean urlDecode,String encoding){
  Map<String,ListOrSingle<String>> map=new LinkedHashMap<>();
  for (  String formField : formText.split("&")) {
    String[] parts=formField.split("=");
    if (parts.length > 1) {
      String key=parts[0];
      String value=urlDecode ? urlDecode(parts[1].trim(),encoding) : parts[1].trim();
      ListOrSingle<String> existing=map.get(key);
      if (existing != null) {
        existing.add(value);
      }
 else {
        map.put(key,ListOrSingle.of(value));
      }
    }
  }
  return map;
}
