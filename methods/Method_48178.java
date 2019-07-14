@Override public String toString(){
  String hn=getSingleHostname();
  return hn.substring(0,Math.min(hn.length(),256)) + ":" + port;
}
