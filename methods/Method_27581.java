@SuppressLint("InflateParams") @OnClick(R.id.sort) public void onSortClicked(){
  if (sort.getTag() != null)   return;
  sort.setTag(true);
  ViewHolder viewHolder=new ViewHolder(LayoutInflater.from(this).inflate(R.layout.simple_list_dialog,null));
  setupPopupWindow(viewHolder);
  ArrayList<String> lists=new ArrayList<>();
  Collections.addAll(lists,getResources().getStringArray(R.array.sort_prs_issues));
  lists.add(CommentsHelper.getThumbsUp());
  lists.add(CommentsHelper.getThumbsDown());
  lists.add(CommentsHelper.getLaugh());
  lists.add(CommentsHelper.getHooray());
  lists.add(CommentsHelper.getSad());
  lists.add(CommentsHelper.getHeart());
  viewHolder.recycler.setAdapter(new SimpleListAdapter<>(lists,new BaseViewHolder.OnItemClickListener<String>(){
    @Override public void onItemClick(    int position,    View v,    String item){
      appendSort(item);
    }
    @Override public void onItemLongClick(    int position,    View v,    String item){
    }
  }
));
  AnimHelper.revealPopupWindow(popupWindow,sort);
}
