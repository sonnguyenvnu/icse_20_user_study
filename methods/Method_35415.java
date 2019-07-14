@OnClick(R.id.button_favorite_toggle) public void onFavoriteToggleAction(View view){
  if (mPlayer == null)   return;
  Song currentSong=mPlayer.getPlayingSong();
  if (currentSong != null) {
    view.setEnabled(false);
    mPresenter.setSongAsFavorite(currentSong,!currentSong.isFavorite());
  }
}
