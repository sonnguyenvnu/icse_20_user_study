public void setAudio(MediaController.AudioEntry entry,boolean divider,boolean checked){
  audioEntry=entry;
  titleTextView.setText(audioEntry.title);
  genreTextView.setText(audioEntry.genre);
  authorTextView.setText(audioEntry.author);
  timeTextView.setText(String.format("%d:%02d",audioEntry.duration / 60,audioEntry.duration % 60));
  setPlayDrawable(MediaController.getInstance().isPlayingMessage(audioEntry.messageObject) && !MediaController.getInstance().isMessagePaused());
  needDivider=divider;
  setWillNotDraw(!divider);
  checkBox.setChecked(checked,false);
}
