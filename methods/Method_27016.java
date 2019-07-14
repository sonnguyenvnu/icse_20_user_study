@Override public C convertToMapped(Class<? extends C> type,String value){
  return RestProvider.gson.fromJson(value,type);
}
