protected CelebrityListHolder createCelebrityListHolder(ViewGroup parent){
  CelebrityListHolder holder=new CelebrityListHolder(ViewUtils.inflate(R.layout.item_fragment_celebrity_list,parent));
  holder.celebrityList.setHasFixedSize(true);
  holder.celebrityList.setLayoutManager(new LinearLayoutManager(parent.getContext(),LinearLayoutManager.HORIZONTAL,false));
  holder.celebrityList.addItemDecoration(new DividerItemDecoration(DividerItemDecoration.HORIZONTAL,R.drawable.transparent_divider_vertical_16dp,holder.celebrityList.getContext()));
  holder.celebrityList.setAdapter(new CelebrityListAdapter());
  return holder;
}
