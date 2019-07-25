@Override public Order<byte[]> clone(){
  final Base64Order o=new Base64Order(this.asc,this.rfc1521compliant);
  o.rotate(this.zero);
  return o;
}
