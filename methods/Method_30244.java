protected void bindCelebrityListHolder(RecyclerView.ViewHolder holder,T item,List<SimpleCelebrity> celebrityList){
  CelebrityListHolder celebrityListHolder=(CelebrityListHolder)holder;
  CelebrityListAdapter adapter=(CelebrityListAdapter)celebrityListHolder.celebrityList.getAdapter();
  adapter.replace(celebrityList);
}
