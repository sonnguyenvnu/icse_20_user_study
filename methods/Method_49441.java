public void addSort(String key,String order,String unmappedType){
  this.sorts.add(ImmutableMap.of(key,new RestSortInfo(order,unmappedType)));
}
