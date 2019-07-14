static public String escapeQname_(String s){
  return s.replaceAll("[.&@%-]","_");
}
