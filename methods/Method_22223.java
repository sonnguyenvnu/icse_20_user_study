private void collectMetrics(@NonNull DisplayMetrics metrics,@NonNull JSONObject container) throws JSONException {
  container.put("density",metrics.density).put("densityDpi",metrics.densityDpi).put("scaledDensity","x" + metrics.scaledDensity).put("widthPixels",metrics.widthPixels).put("heightPixels",metrics.heightPixels).put("xdpi",metrics.xdpi).put("ydpi",metrics.ydpi);
}
