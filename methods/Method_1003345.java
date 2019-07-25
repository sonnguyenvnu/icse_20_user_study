private static String sanitize(String label){
  return label == null ? null : label.trim().toUpperCase(Locale.ENGLISH);
}
