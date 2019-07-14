private static Pattern initializePattern(){
  return Pattern.compile("url\\(\\s*'([^\\)]+)'\\s*\\)" + "|url\\(\\s*\"([^\\)]+)\"\\s*\\)" + "|url\\(\\s*([^\\)]+)\\s*\\)" + "|\\/\\*(\\*(?!\\/)|[^*])*\\*\\/");
}
