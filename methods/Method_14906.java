@Override public CommentView createView(int position,ViewGroup parent){
  return new CommentView(context,resources).setOnCommentClickListener(onCommentClickListener).setOnShowAllListener(new OnShowAllListener(){
    @Override public void onShowAll(    int position,    CommentView bv,    boolean show){
      showAllMap.put(position,show);
      bindView(position,bv);
    }
  }
);
}
