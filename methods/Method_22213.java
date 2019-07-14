@NonNull private JSONObject collectDisplayData(@NonNull Display display) throws JSONException {
  final DisplayMetrics metrics=new DisplayMetrics();
  display.getMetrics(metrics);
  final JSONObject result=new JSONObject();
  collectCurrentSizeRange(display,result);
  collectFlags(display,result);
  collectMetrics(display,result);
  collectRealMetrics(display,result);
  collectName(display,result);
  collectRealSize(display,result);
  collectRectSize(display,result);
  collectSize(display,result);
  collectRotation(display,result);
  collectIsValid(display,result);
  result.put("orientation",display.getRotation()).put("refreshRate",display.getRefreshRate());
  result.put("height",display.getHeight()).put("width",display.getWidth()).put("pixelFormat",display.getPixelFormat());
  return result;
}
