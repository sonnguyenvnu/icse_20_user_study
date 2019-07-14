private void putNA(@NonNull String key){
  try {
    content.put(key,ACRAConstants.NOT_AVAILABLE);
  }
 catch (  JSONException ignored) {
  }
}
