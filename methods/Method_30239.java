protected ItemCollectionListHolder createItemCollectionListHolder(ViewGroup parent){
  ItemCollectionListHolder holder=new ItemCollectionListHolder(ViewUtils.inflate(R.layout.item_fragment_collection_list,parent));
  holder.itemCollectionList.setAdapter(new ItemCollectionListAdapter(mListener));
  return holder;
}
