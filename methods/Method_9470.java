private void updateVideoPlayerTime(){
  String newText;
  if (videoPlayer == null) {
    newText=String.format("%02d:%02d / %02d:%02d",0,0,0,0);
  }
 else {
    long current=videoPlayer.getCurrentPosition();
    if (current < 0) {
      current=0;
    }
    long total=videoPlayer.getDuration();
    if (total < 0) {
      total=0;
    }
    if (total != C.TIME_UNSET && current != C.TIME_UNSET) {
      if (!inPreview && videoTimelineView.getVisibility() == View.VISIBLE) {
        total*=(videoTimelineView.getRightProgress() - videoTimelineView.getLeftProgress());
        current-=videoTimelineView.getLeftProgress() * total;
        if (current > total) {
          current=total;
        }
      }
      current/=1000;
      total/=1000;
      newText=String.format("%02d:%02d / %02d:%02d",current / 60,current % 60,total / 60,total % 60);
    }
 else {
      newText=String.format("%02d:%02d / %02d:%02d",0,0,0,0);
    }
  }
  videoPlayerTime.setText(newText);
}
