/** 
 * Unbind the adapter and layoutManger to recyclerView. And also set null to them.
 */
public void unbindView(){
  if (mContentView != null) {
    this.mContentView.setAdapter(null);
    this.mContentView.setLayoutManager(null);
    this.mContentView=null;
  }
}
