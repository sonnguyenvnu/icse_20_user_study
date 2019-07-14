@Override public void bindView(int position,CommentView bv){
  bv.setShowAll(showAll ? Boolean.valueOf(true) : showAllMap.get(position));
  super.bindView(position,bv);
}
