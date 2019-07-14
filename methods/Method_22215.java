private void collectRotation(@NonNull Display display,JSONObject container) throws JSONException {
  container.put("rotation",rotationToString(display.getRotation()));
}
