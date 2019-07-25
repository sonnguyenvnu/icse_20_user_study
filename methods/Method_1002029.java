public static void open(){
  LinkedList<Z> list=instances2.get();
  if (list == null) {
    list=new LinkedList<Z>();
    instances2.set(list);
  }
  list.addFirst(new Z());
}
