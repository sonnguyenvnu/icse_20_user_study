@Override protected View createFooterView(){
  if (mFooterView == null) {
    mFooterView=View.inflate(mContext,R.layout.item_local_folder_footer,null);
    View layoutAddFolder=mFooterView.findViewById(R.id.layout_add_folder);
    layoutAddFolder.setOnClickListener(new View.OnClickListener(){
      @Override public void onClick(      View v){
        if (mAddFolderCallback != null) {
          mAddFolderCallback.onAddFolder();
        }
      }
    }
);
    textViewSummary=(TextView)mFooterView.findViewById(R.id.text_view_summary);
  }
  updateFooterView();
  return mFooterView;
}
