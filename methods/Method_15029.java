@SuppressLint("InflateParams") @Override public View createView(LayoutInflater inflater){
  convertView=inflater.inflate(R.layout.user_view,null);
  ivUserViewHead=findViewById(R.id.ivUserViewHead,this);
  tvUserViewSex=findViewById(R.id.tvUserViewSex,this);
  tvUserViewName=findViewById(R.id.tvUserViewName,this);
  tvUserViewId=findViewById(R.id.tvUserViewId);
  tvUserViewTag=findViewById(R.id.tvUserViewTag,this);
  return convertView;
}
