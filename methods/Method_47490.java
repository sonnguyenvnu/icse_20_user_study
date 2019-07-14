@Override public String getAuthority(){
  return this.url + ";" + this.method;
}
