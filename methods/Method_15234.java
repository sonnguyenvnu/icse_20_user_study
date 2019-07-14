@SuppressLint("InflateParams") @Override public View createView(LayoutInflater inflater){
  this.inflater=inflater;
  convertView=inflater.inflate(R.layout.top_tab_view,null);
  tvTopTabViewTabFirst=(TextView)findViewById(R.id.tvTopTabViewTabFirst);
  tvTopTabViewTabLast=(TextView)findViewById(R.id.tvTopTabViewTabLast);
  llTopTabViewContainer=(LinearLayout)findViewById(R.id.llTopTabViewContainer);
  return convertView;
}
