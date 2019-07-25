public static Header create(NutMap reHeader){
  Header header=new Header();
  header.items.putAll(reHeader);
  return header;
}
