public static void toggleShuffleMusic(int type){
  if (type == 2) {
    shuffleMusic=!shuffleMusic;
  }
 else {
    playOrderReversed=!playOrderReversed;
  }
  MediaController.getInstance().checkIsNextMediaFileDownloaded();
  SharedPreferences preferences=MessagesController.getGlobalMainSettings();
  SharedPreferences.Editor editor=preferences.edit();
  editor.putBoolean("shuffleMusic",shuffleMusic);
  editor.putBoolean("playOrderReversed",playOrderReversed);
  editor.commit();
}
