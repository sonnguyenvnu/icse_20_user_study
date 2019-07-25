/** 
 * Audio recordings playback
 */
private void playback(View v,Uri uri){
  if (mPlayer != null && mPlayer.isPlaying()) {
    if (isPlayingView != v) {
      stopPlaying();
      isPlayingView=v;
      startPlaying(uri);
      replacePlayingAudioBitmap(v);
    }
 else {
      stopPlaying();
    }
  }
 else {
    isPlayingView=v;
    startPlaying(uri);
    replacePlayingAudioBitmap(v);
  }
}
