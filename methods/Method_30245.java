protected void bindAwardListHolder(RecyclerView.ViewHolder holder,T item,List<ItemAwardItem> awardList){
  AwardListHolder awardListHolder=(AwardListHolder)holder;
  ViewUtils.setVisibleOrGone(awardListHolder.awardList,!awardList.isEmpty());
  ItemAwardListAdapter adapter=(ItemAwardListAdapter)awardListHolder.awardList.getAdapter();
  adapter.replace(awardList);
  awardListHolder.itemView.setOnClickListener(view -> {
    UriHandler.open(item.url + "awards/",view.getContext());
  }
);
}
