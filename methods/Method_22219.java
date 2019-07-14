private void collectRealSize(@NonNull Display display,@NonNull JSONObject container) throws JSONException {
  if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
    final Point size=new Point();
    display.getRealSize(size);
    container.put("realSize",new JSONArray(Arrays.asList(size.x,size.y)));
  }
}
