@Override public void updatePlayMode(PlayMode playMode){
  if (playMode == null) {
    playMode=PlayMode.getDefault();
  }
switch (playMode) {
case LIST:
    buttonPlayModeToggle.setImageResource(R.drawable.ic_play_mode_list);
  break;
case LOOP:
buttonPlayModeToggle.setImageResource(R.drawable.ic_play_mode_loop);
break;
case SHUFFLE:
buttonPlayModeToggle.setImageResource(R.drawable.ic_play_mode_shuffle);
break;
case SINGLE:
buttonPlayModeToggle.setImageResource(R.drawable.ic_play_mode_single);
break;
}
}
