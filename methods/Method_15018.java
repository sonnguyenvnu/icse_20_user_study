@SuppressLint("InflateParams") @Override public View createView(LayoutInflater inflater){
  this.inflater=inflater;
  convertView=inflater.inflate(R.layout.moment_view,null);
  llMomentViewContainer=findViewById(R.id.llMomentViewContainer);
  ivMomentViewHead=findViewById(R.id.ivMomentViewHead,this);
  tvMomentViewName=findViewById(R.id.tvMomentViewName,this);
  tvMomentViewStatus=findViewById(R.id.tvMomentViewStatus,this);
  tvMomentViewContent=findViewById(R.id.tvMomentViewContent,this);
  gvMomentView=findViewById(R.id.gvMomentView);
  tvMomentViewDate=findViewById(R.id.tvMomentViewDate);
  ivMomentViewPraise=findViewById(R.id.ivMomentViewPraise,this);
  ivMomentViewComment=findViewById(R.id.ivMomentViewComment,this);
  llMomentViewPraise=findViewById(R.id.llMomentViewPraise,this);
  tvMomentViewPraise=findViewById(R.id.tvMomentViewPraise,this);
  vMomentViewDivider=findViewById(R.id.vMomentViewDivider);
  llMomentViewCommentContainer=findViewById(R.id.llMomentViewCommentContainer);
  return convertView;
}
