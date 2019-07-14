@SuppressLint("InflateParams") @Override public View createView(LayoutInflater inflater){
  this.inflater=inflater;
  convertView=inflater.inflate(R.layout.comment_container_view,null);
  llCommentContainerViewContainer=findViewById(R.id.llCommentContainerViewContainer);
  tvCommentContainerViewMore=findViewById(R.id.tvCommentContainerViewMore);
  return convertView;
}
