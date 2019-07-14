private void addCardStyle(JSONObject cardData,Card card){
  try {
    GsonBuilder gsonBuilder=new GsonBuilder();
    gsonBuilder.registerTypeAdapter(Float.class,new JsonSerializer<Float>(){
      @Override public JsonElement serialize(      final Float src,      final Type typeOfSrc,      final JsonSerializationContext context){
        try {
          if (src.isInfinite() || src.isNaN()) {
            return new JsonPrimitive(0f);
          }
          BigDecimal value=BigDecimal.valueOf(src);
          return new JsonPrimitive(value);
        }
 catch (        Exception e) {
          e.printStackTrace();
        }
        return new JsonPrimitive(0f);
      }
    }
);
    Gson gson=gsonBuilder.create();
    GridCard.GridStyle gridStyle=new GridCard.GridStyle();
    if (card instanceof BannerCard) {
      gridStyle.aspectRatio=3.223f;
    }
    cardData.put(Card.KEY_STYLE,new JSONObject(gson.toJson(gridStyle)));
  }
 catch (  JSONException e) {
    e.printStackTrace();
  }
}
