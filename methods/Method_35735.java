private static String sanitise(String s){
  return NON_ALPHANUMERIC.matcher(s).replaceAll("");
}
