protected void bindRelatedDoulistListHolder(RecyclerView.ViewHolder holder,T item,List<Doulist> relatedDoulistList){
  RelatedDoulistListHolder relatedDoulistListHolder=(RelatedDoulistListHolder)holder;
  relatedDoulistListHolder.titleLayout.setOnClickListener(view -> {
    UriHandler.open(item.url + "doulists",view.getContext());
  }
);
  ViewUtils.setVisibleOrGone(relatedDoulistListHolder.relatedDoulistList,!relatedDoulistList.isEmpty());
  ItemRelatedDoulistListAdapter adapter=(ItemRelatedDoulistListAdapter)relatedDoulistListHolder.relatedDoulistList.getAdapter();
  relatedDoulistList=relatedDoulistList.subList(0,Math.min(ITEM_RELATED_DOULIST_LIST_MAX_SIZE,relatedDoulistList.size()));
  adapter.replace(relatedDoulistList);
}
