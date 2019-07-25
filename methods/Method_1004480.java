private RemoteMessageQuery deserialize(String json){
  try {
    return serializer.fromJson(json,RemoteMessageQuery.class);
  }
 catch (  JsonSyntaxException e) {
    LOG.error("Deserialize query json error.",e);
    return null;
  }
}
