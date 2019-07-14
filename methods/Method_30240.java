protected RelatedDoulistListHolder createRelatedDoulistListHolder(ViewGroup parent){
  RelatedDoulistListHolder holder=new RelatedDoulistListHolder(ViewUtils.inflate(R.layout.item_fragment_related_doulist_list,parent));
  holder.relatedDoulistList.setAdapter(new ItemRelatedDoulistListAdapter());
  return holder;
}
