public static boolean hasSourceRetention(Element element){
  return effectiveRetentionPolicy(element) == RetentionPolicy.SOURCE;
}
