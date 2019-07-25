public OrderBy asc(String name){
  OrderByItem asc=new OrderByItem(name,"ASC");
  asc.setPojo(pojo);
  list.add(asc);
  return this;
}
