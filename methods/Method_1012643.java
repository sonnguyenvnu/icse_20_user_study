public static void main(String[] args){
  eq("f1","192.168.0.1,192.168.0.5",new IpFilter(" 192.168.0.1, 192.168.0.5").getNormalizedFilter());
  eq("f2","192.168.0.*,192.1-6.3-.5",new IpFilter(" 192.168.0.*, 192.1-6.3-.5").getNormalizedFilter());
  eq("f3","2-3.5,myhost",new IpFilter(" 3-2. 5;myhost").getNormalizedFilter());
}
