private String getErrorsString(HashMap<String,String> errors,HashMap<String,String> documentErrors){
  StringBuilder stringBuilder=new StringBuilder();
  for (int a=0; a < 2; a++) {
    HashMap<String,String> hashMap;
    if (a == 0) {
      hashMap=errors;
    }
 else {
      hashMap=documentErrors;
    }
    if (hashMap == null) {
      continue;
    }
    for (    HashMap.Entry<String,String> entry : hashMap.entrySet()) {
      String value=entry.getValue();
      if (stringBuilder.length() > 0) {
        stringBuilder.append(", ");
        value=value.toLowerCase();
      }
      if (value.endsWith(".")) {
        value=value.substring(0,value.length() - 1);
      }
      stringBuilder.append(value);
    }
  }
  if (stringBuilder.length() > 0) {
    stringBuilder.append('.');
  }
  return stringBuilder.toString();
}
