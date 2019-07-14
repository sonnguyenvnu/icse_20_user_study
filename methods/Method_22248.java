public synchronized void put(@NonNull ReportField key,@Nullable JSONObject value){
  put(key.toString(),value);
}
