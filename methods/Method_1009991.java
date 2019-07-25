private void refresh(){
  getIntent().removeExtra("comments");
  setResult(RESULT_OK);
  mCommitComments.clear();
  loadComments(false,true);
  setContentShown(false);
}
