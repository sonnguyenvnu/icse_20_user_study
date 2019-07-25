public static BaseComponent[] parse(String json){
  JsonElement jsonElement=JSON_PARSER.parse(json);
  if (jsonElement.isJsonArray()) {
    return gson.fromJson(jsonElement,BaseComponent[].class);
  }
 else {
    return new BaseComponent[]{gson.fromJson(jsonElement,BaseComponent.class)};
  }
}
