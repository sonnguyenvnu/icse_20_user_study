protected void bindItemCollectionListHolder(RecyclerView.ViewHolder holder,T item,List<SimpleItemCollection> itemCollectionList,@NonNull List<Object> payloads){
  if (payloads.isEmpty()) {
    bindItemCollectionListHolder(holder,item,itemCollectionList);
  }
 else {
    for (    List<Object> payload : (List<List<Object>>)(Object)payloads) {
      int position=(int)payload.get(0);
      SimpleItemCollection newItemCollection=(SimpleItemCollection)CollectionUtils.getOrNull(payload,1);
      bindItemCollectionListHolder(holder,position,newItemCollection);
    }
  }
}
