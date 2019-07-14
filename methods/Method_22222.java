private void collectName(@NonNull Display display,@NonNull JSONObject container) throws JSONException {
  if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
    container.put("name",display.getName());
  }
}
