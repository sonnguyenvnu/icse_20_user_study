private void onConfirm(){
  if (mCallback == null)   return;
  PlayList playList=mPlayList;
  if (playList == null) {
    playList=new PlayList();
  }
  playList.setName(editTextName.getText().toString());
  if ((isEditMode())) {
    mCallback.onEdited(playList);
  }
 else {
    mCallback.onCreated(playList);
  }
}
