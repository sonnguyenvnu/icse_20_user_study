private void putKeyValue(@NonNull CrashReportData crashData,@NonNull String key,@NonNull String value){
  try {
    crashData.put(key,new JSONObject(value));
  }
 catch (  JSONException e1) {
    try {
      crashData.put(key,Double.valueOf(value));
    }
 catch (    NumberFormatException e2) {
switch (value) {
case "true":
        crashData.put(key,true);
      break;
case "false":
    crashData.put(key,false);
  break;
default :
crashData.put(key,value);
break;
}
}
}
}
