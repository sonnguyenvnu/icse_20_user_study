private void updateProgress(MessageObject messageObject){
  if (seekBarView != null) {
    if (!seekBarView.isDragging()) {
      seekBarView.setProgress(messageObject.audioProgress);
      seekBarView.setBufferedProgress(messageObject.bufferedProgress);
    }
    if (lastTime != messageObject.audioProgressSec) {
      lastTime=messageObject.audioProgressSec;
      timeTextView.setText(String.format("%d:%02d",messageObject.audioProgressSec / 60,messageObject.audioProgressSec % 60));
    }
  }
}
