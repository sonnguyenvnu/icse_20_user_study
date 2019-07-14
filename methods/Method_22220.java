private void collectCurrentSizeRange(@NonNull Display display,@NonNull JSONObject container) throws JSONException {
  if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
    final Point smallest=new Point();
    final Point largest=new Point();
    display.getCurrentSizeRange(smallest,largest);
    final JSONObject result=new JSONObject();
    result.put("smallest",new JSONArray(Arrays.asList(smallest.x,smallest.y)));
    result.put("largest",new JSONArray(Arrays.asList(largest.x,largest.y)));
    container.put("currentSizeRange",result);
  }
}
