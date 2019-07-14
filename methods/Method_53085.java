private void addMediaEntitySizeIfNotNull(Map<Integer,MediaEntity.Size> sizes,JSONObject sizesJSON,Integer size,String key) throws JSONException {
  if (!sizesJSON.isNull(key)) {
    sizes.put(size,new Size(sizesJSON.getJSONObject(key)));
  }
}
