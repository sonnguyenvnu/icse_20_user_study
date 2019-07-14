@SuppressWarnings("unchecked") private static Map<String,Object> convertHealth(Map<String,Object> body){
  Map<String,Object> converted=new LinkedHashMap<>();
  Map<String,Object> details=new LinkedHashMap<>();
  body.forEach((key,value) -> {
    if ("status".equals(key)) {
      converted.put(key,value);
    }
 else     if (value instanceof Map) {
      details.put(key,convertHealth((Map<String,Object>)value));
    }
 else {
      details.put(key,value);
    }
  }
);
  if (!details.isEmpty()) {
    converted.put("details",details);
  }
  return converted;
}
