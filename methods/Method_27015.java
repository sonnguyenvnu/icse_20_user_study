@Override public String convertToPersisted(C value){
  return RestProvider.gson.toJson(value);
}
