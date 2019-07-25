public <T>void insert(Table table,Mapper<T> mapper,T element){
  insert(table,mapper,Collections.singletonList(element));
}
