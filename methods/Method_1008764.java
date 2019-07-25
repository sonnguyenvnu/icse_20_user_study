public void init(Context context){
  mHandler=new Handler();
  setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT));
  setDrawSelectorOnTop(false);
  mContext=context;
  setUpListView();
}
