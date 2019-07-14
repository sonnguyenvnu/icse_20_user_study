/** 
 * ???????
 */
private void initUI(){
  mListView=getContentView().findViewById(R.id.title_list);
  mListView.setOnItemClickListener(new OnItemClickListener(){
    @Override public void onItemClick(    AdapterView<?> arg0,    View arg1,    int index,    long arg3){
      dismiss();
      if (mItemOnClickListener != null) {
        mItemOnClickListener.onItemClick(mActionItems.get(index),index);
      }
    }
  }
);
}
