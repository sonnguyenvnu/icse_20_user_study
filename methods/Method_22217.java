private void collectRectSize(@NonNull Display display,@NonNull JSONObject container) throws JSONException {
  final Rect size=new Rect();
  display.getRectSize(size);
  container.put("rectSize",new JSONArray(Arrays.asList(size.top,size.left,size.width(),size.height())));
}
