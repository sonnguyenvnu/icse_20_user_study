private void updateTitle(boolean shutdown){
  MessageObject messageObject=MediaController.getInstance().getPlayingMessageObject();
  if (messageObject == null && shutdown || messageObject != null && !messageObject.isMusic()) {
    dismiss();
  }
 else {
    if (messageObject == null) {
      return;
    }
    if (messageObject.eventId != 0 || messageObject.getId() <= -2000000000) {
      hasOptions=false;
      menuItem.setVisibility(View.INVISIBLE);
      optionsButton.setVisibility(View.INVISIBLE);
    }
 else {
      hasOptions=true;
      if (!actionBar.isSearchFieldVisible()) {
        menuItem.setVisibility(View.VISIBLE);
      }
      optionsButton.setVisibility(View.VISIBLE);
    }
    checkIfMusicDownloaded(messageObject);
    updateProgress(messageObject);
    if (MediaController.getInstance().isMessagePaused()) {
      playButton.setImageDrawable(Theme.createSimpleSelectorDrawable(playButton.getContext(),R.drawable.pl_play,Theme.getColor(Theme.key_player_button),Theme.getColor(Theme.key_player_buttonActive)));
      playButton.setContentDescription(LocaleController.getString("AccActionPlay",R.string.AccActionPlay));
    }
 else {
      playButton.setImageDrawable(Theme.createSimpleSelectorDrawable(playButton.getContext(),R.drawable.pl_pause,Theme.getColor(Theme.key_player_button),Theme.getColor(Theme.key_player_buttonActive)));
      playButton.setContentDescription(LocaleController.getString("AccActionPause",R.string.AccActionPause));
    }
    String title=messageObject.getMusicTitle();
    String author=messageObject.getMusicAuthor();
    titleTextView.setText(title);
    authorTextView.setText(author);
    actionBar.setTitle(title);
    actionBar.setSubtitle(author);
    String loadTitle=author + " " + title;
    AudioInfo audioInfo=MediaController.getInstance().getAudioInfo();
    if (audioInfo != null && audioInfo.getCover() != null) {
      hasNoCover=0;
      placeholderImageView.setImageBitmap(audioInfo.getCover());
    }
 else {
      String artworkUrl=messageObject.getArtworkUrl(false);
      if (!TextUtils.isEmpty(artworkUrl)) {
        placeholderImageView.setImage(artworkUrl,null,null);
        hasNoCover=2;
      }
 else {
        placeholderImageView.setImageDrawable(null);
        hasNoCover=1;
      }
      placeholderImageView.invalidate();
    }
    if (durationTextView != null) {
      int duration=messageObject.getDuration();
      durationTextView.setText(duration != 0 ? String.format("%d:%02d",duration / 60,duration % 60) : "-:--");
    }
  }
}
