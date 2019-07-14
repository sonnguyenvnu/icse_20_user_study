@NonNull private String sanitizeFilename(String name){
  String s=name.replaceAll("[^ a-zA-Z0-9\\._-]+","");
  return s.substring(0,Math.min(s.length(),100));
}
