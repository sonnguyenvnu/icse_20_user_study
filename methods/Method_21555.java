public void addOrderBy(String nestedPath,String name,String type,ScriptSortBuilder.ScriptSortType scriptSortType){
  if ("_score".equals(name)) {
    isQuery=true;
  }
  Order order=new Order(nestedPath,name,type);
  order.setScriptSortType(scriptSortType);
  this.orderBys.add(order);
}
