public static TLClassStore Instance(){
  if (store == null) {
    store=new TLClassStore();
  }
  return store;
}
