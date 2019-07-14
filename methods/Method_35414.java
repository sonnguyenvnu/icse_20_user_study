@OnClick(R.id.button_play_toggle) public void onPlayToggleAction(View view){
  if (mPlayer == null)   return;
  if (mPlayer.isPlaying()) {
    mPlayer.pause();
  }
 else {
    mPlayer.play();
  }
}
