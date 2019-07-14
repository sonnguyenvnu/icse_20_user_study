@Override protected View createFooterView(){
  if (mFooterView == null) {
    mFooterView=View.inflate(mContext,R.layout.item_play_list_footer,null);
    View layoutAddPlayList=mFooterView.findViewById(R.id.layout_add_play_list);
    layoutAddPlayList.setOnClickListener(new View.OnClickListener(){
      @Override public void onClick(      View v){
        if (mAddPlayListCallback != null) {
          mAddPlayListCallback.onAddPlayList();
        }
      }
    }
);
    textViewSummary=(TextView)mFooterView.findViewById(R.id.text_view_summary);
  }
  updateFooterView();
  return mFooterView;
}
