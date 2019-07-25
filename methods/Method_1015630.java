public RouterStub set(String attr,Object val){
switch (attr) {
case "tcp_nodelay":
    tcpNoDelay((Boolean)val);
  break;
default :
throw new IllegalArgumentException("Attribute " + attr + " unknown");
}
return this;
}
