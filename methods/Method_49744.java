static String getFFmpeg(Context context,Map<String,String> environmentVars){
  String ffmpegCommand="";
  if (environmentVars != null) {
    for (    Map.Entry<String,String> var : environmentVars.entrySet()) {
      ffmpegCommand+=var.getKey() + "=" + var.getValue() + " ";
    }
  }
  ffmpegCommand+=getFFmpeg(context);
  return ffmpegCommand;
}
