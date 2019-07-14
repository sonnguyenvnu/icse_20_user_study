private void didChangedCompressionLevel(boolean request){
  SharedPreferences preferences=MessagesController.getGlobalMainSettings();
  SharedPreferences.Editor editor=preferences.edit();
  editor.putInt("compress_video2",selectedCompression);
  editor.commit();
  updateWidthHeightBitrateForCompression();
  updateVideoInfo();
  if (request) {
    requestVideoPreview(1);
  }
}
