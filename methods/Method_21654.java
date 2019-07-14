/** 
 * Add sorts to the elasticsearch query based on the 'ORDER BY' clause.
 * @param orderBys list of Order object
 */
private void setSorts(List<Order> orderBys){
  for (  Order order : orderBys) {
    if (order.getNestedPath() != null) {
      request.addSort(SortBuilders.fieldSort(order.getName()).order(SortOrder.valueOf(order.getType())).setNestedSort(new NestedSortBuilder(order.getNestedPath())));
    }
 else     if (order.getName().contains("script(")) {
      String scriptStr=order.getName().substring("script(".length(),order.getName().length() - 1);
      Script script=new Script(scriptStr);
      ScriptSortBuilder scriptSortBuilder=SortBuilders.scriptSort(script,order.getScriptSortType());
      scriptSortBuilder=scriptSortBuilder.order(SortOrder.valueOf(order.getType()));
      request.addSort(scriptSortBuilder);
    }
 else {
      request.addSort(order.getName(),SortOrder.valueOf(order.getType()));
    }
  }
}
