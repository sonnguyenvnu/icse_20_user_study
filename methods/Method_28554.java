@NonNull private static String getFormattedSource(@NonNull String source){
  return source.replaceAll("<","&lt;").replaceAll(">","&gt;");
}
