private void updateVideoPlayerTime(){
  String newText;
  if (videoPlayer == null) {
    newText=String.format("%02d:%02d / %02d:%02d",0,0,0,0);
  }
 else {
    long current=videoPlayer.getCurrentPosition() / 1000;
    long total=videoPlayer.getDuration();
    total/=1000;
    if (total != C.TIME_UNSET && current != C.TIME_UNSET) {
      newText=String.format("%02d:%02d / %02d:%02d",current / 60,current % 60,total / 60,total % 60);
    }
 else {
      newText=String.format("%02d:%02d / %02d:%02d",0,0,0,0);
    }
  }
  if (!TextUtils.equals(videoPlayerTime.getText(),newText)) {
    videoPlayerTime.setText(newText);
  }
}
