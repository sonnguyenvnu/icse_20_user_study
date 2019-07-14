@Override public void onOpenForResult(int position,CommitFileChanges model){
  FullScreenFileChangeActivity.Companion.startActivityForResult(this,model,position,true);
}
