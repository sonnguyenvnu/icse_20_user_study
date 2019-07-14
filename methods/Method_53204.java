static SymbolEntity[] getSymbols(JSONObject entities) throws JSONException, TwitterException {
  if (!entities.isNull("symbols")) {
    JSONArray symbolsArray=entities.getJSONArray("symbols");
    int len=symbolsArray.length();
    SymbolEntity[] symbolEntities=new SymbolEntity[len];
    for (int i=0; i < len; i++) {
      symbolEntities[i]=new HashtagEntityJSONImpl(symbolsArray.getJSONObject(i));
    }
    return symbolEntities;
  }
 else {
    return null;
  }
}
