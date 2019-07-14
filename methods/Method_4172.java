@RequiresApi(26) private void abandonAudioFocusV26(){
  if (audioFocusRequest != null) {
    audioManager.abandonAudioFocusRequest(audioFocusRequest);
  }
}
