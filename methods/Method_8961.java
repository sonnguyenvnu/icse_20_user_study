public void updateProgress(){
  if (currentMessageObject == null) {
    return;
  }
  if (!seekBar.isDragging()) {
    seekBar.setProgress(currentMessageObject.audioProgress);
  }
  int duration=0;
  if (!MediaController.getInstance().isPlayingMessage(currentMessageObject)) {
    for (int a=0; a < currentMessageObject.getDocument().attributes.size(); a++) {
      TLRPC.DocumentAttribute attribute=currentMessageObject.getDocument().attributes.get(a);
      if (attribute instanceof TLRPC.TL_documentAttributeAudio) {
        duration=attribute.duration;
        break;
      }
    }
  }
 else {
    duration=currentMessageObject.audioProgressSec;
  }
  String timeString=String.format("%02d:%02d",duration / 60,duration % 60);
  if (lastTimeString == null || lastTimeString != null && !lastTimeString.equals(timeString)) {
    timeWidth=(int)Math.ceil(timePaint.measureText(timeString));
    timeLayout=new StaticLayout(timeString,timePaint,timeWidth,Layout.Alignment.ALIGN_NORMAL,1.0f,0.0f,false);
  }
  invalidate();
}
