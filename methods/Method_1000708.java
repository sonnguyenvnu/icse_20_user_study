public SimpleContext clone(){
  SimpleContext context=new SimpleContext();
  context.map.putAll(this.map);
  return context;
}
