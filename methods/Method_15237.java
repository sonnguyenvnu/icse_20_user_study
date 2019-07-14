@Override public void bindView(String[] names){
  if (names == null || names.length < 2) {
    Log.e(TAG,"setInerView names == null || names.length < 2 >> return; ");
    return;
  }
  this.names=names;
  this.lastPosition=getCount() - 1;
  tvTabs=new TextView[getCount()];
  tvTabs[0]=tvTopTabViewTabFirst;
  tvTabs[lastPosition]=tvTopTabViewTabLast;
  llTopTabViewContainer.removeAllViews();
  for (int i=0; i < tvTabs.length; i++) {
    final int position=i;
    if (tvTabs[position] == null) {
      tvTabs[position]=(TextView)inflater.inflate(R.layout.top_tab_tv_center,llTopTabViewContainer,false);
      llTopTabViewContainer.addView(tvTabs[position]);
      View divider=inflater.inflate(R.layout.divider_vertical_1dp,llTopTabViewContainer,false);
      divider.setBackgroundColor(getColor(R.color.white));
      llTopTabViewContainer.addView(divider);
    }
    tvTabs[position].setText(StringUtil.getTrimedString(names[position]));
    tvTabs[position].setOnClickListener(new OnClickListener(){
      @Override public void onClick(      View v){
        select(position);
      }
    }
);
    width=tvTabs[position].getWidth();
    if (minWidth < width) {
      minWidth=width;
    }
  }
  maxWidth=llTopTabViewContainer.getMeasuredWidth() / tvTabs.length;
  if (minWidth > maxWidth) {
    minWidth=maxWidth;
  }
  for (int i=0; i < tvTabs.length; i++) {
    tvTabs[i].setMinWidth(minWidth);
    if (tvTabs[i].getWidth() > maxWidth) {
      tvTabs[i].setWidth(maxWidth);
    }
  }
  select(currentPosition);
}
