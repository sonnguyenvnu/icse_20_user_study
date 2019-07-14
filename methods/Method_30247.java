private void bindItemCollectionListHolder(RecyclerView.ViewHolder holder,T item,List<SimpleItemCollection> itemCollectionList){
  ItemCollectionListHolder itemCollectionListHolder=(ItemCollectionListHolder)holder;
  itemCollectionListHolder.createButton.setOnClickListener(view -> {
    Context context=view.getContext();
    context.startActivity(ItemCollectionActivity.makeIntent(item,context));
  }
);
  itemCollectionList=itemCollectionList.subList(0,Math.min(ITEM_COLLECTION_LIST_MAX_SIZE,itemCollectionList.size()));
  ViewUtils.setVisibleOrGone(itemCollectionListHolder.itemCollectionList,!itemCollectionList.isEmpty());
  ItemCollectionListAdapter adapter=(ItemCollectionListAdapter)itemCollectionListHolder.itemCollectionList.getAdapter();
  adapter.setItem(item);
  adapter.replace(itemCollectionList);
  itemCollectionListHolder.viewMoreButton.setOnClickListener(view -> {
    UriHandler.open(item.url + "collections",view.getContext());
  }
);
}
