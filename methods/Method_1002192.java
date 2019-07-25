private void inflate(){
  mDebugViewGroup.removeAllViews();
  LayoutInflater inflater=LayoutInflater.from(mContext);
  inflater.inflate(R.layout.debug_view,mDebugViewGroup,true);
  column1=(LinearLayout)mDebugViewGroup.findViewById(R.id.debug_view_column1);
  column2=(LinearLayout)mDebugViewGroup.findViewById(R.id.debug_view_column2);
}
