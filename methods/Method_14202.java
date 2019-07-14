static protected List<String> toExpressionList(Object o){
  return o == null ? new ArrayList<String>() : ((TopList)o).getList();
}
