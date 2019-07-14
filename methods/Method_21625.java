private List<From> findJoinedFrom(SQLTableSource from){
  SQLJoinTableSource joinTableSource=((SQLJoinTableSource)from);
  List<From> fromList=new ArrayList<>();
  fromList.addAll(findFrom(joinTableSource.getLeft()));
  fromList.addAll(findFrom(joinTableSource.getRight()));
  return fromList;
}
