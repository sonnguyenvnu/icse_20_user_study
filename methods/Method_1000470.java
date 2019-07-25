public OrderBy desc(String name){
  OrderByItem desc=new OrderByItem(name,"DESC");
  desc.setPojo(pojo);
  list.add(desc);
  return this;
}
