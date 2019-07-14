public String getHTTPHeader(){
  String fmt="%s=%s; expires=%s";
  return String.format(fmt,this.n,this.v,this.e);
}
