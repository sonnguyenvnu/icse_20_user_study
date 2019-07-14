public void stopRecording(final int send){
  if (recordStartRunnable != null) {
    recordQueue.cancelRunnable(recordStartRunnable);
    recordStartRunnable=null;
  }
  recordQueue.postRunnable(() -> {
    if (audioRecorder == null) {
      return;
    }
    try {
      sendAfterDone=send;
      audioRecorder.stop();
    }
 catch (    Exception e) {
      FileLog.e(e);
      if (recordingAudioFile != null) {
        recordingAudioFile.delete();
      }
    }
    if (send == 0) {
      stopRecordingInternal(0);
    }
    try {
      feedbackView.performHapticFeedback(HapticFeedbackConstants.KEYBOARD_TAP,HapticFeedbackConstants.FLAG_IGNORE_GLOBAL_SETTING);
    }
 catch (    Exception ignore) {
    }
    AndroidUtilities.runOnUIThread(() -> NotificationCenter.getInstance(recordingCurrentAccount).postNotificationName(NotificationCenter.recordStopped,send == 2 ? 1 : 0));
  }
);
}
