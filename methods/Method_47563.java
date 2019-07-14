/** 
 * Creates a new adapter if necessary and sets up its parameters. Override this method to provide a custom adapter.
 */
protected void refreshAdapter(){
  if (mAdapter == null) {
    mAdapter=createMonthAdapter(getContext(),mController);
  }
 else {
    mAdapter.setSelectedDay(mSelectedDay);
  }
  setAdapter(mAdapter);
}
