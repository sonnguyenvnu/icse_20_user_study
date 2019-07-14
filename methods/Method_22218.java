private void collectSize(@NonNull Display display,@NonNull JSONObject container) throws JSONException {
  final Point size=new Point();
  display.getSize(size);
  container.put("size",new JSONArray(Arrays.asList(size.x,size.y)));
}
