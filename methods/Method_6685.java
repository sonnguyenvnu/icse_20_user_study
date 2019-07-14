private void stopRecordingInternal(final int send){
  if (send != 0) {
    final TLRPC.TL_document audioToSend=recordingAudio;
    final File recordingAudioFileToSend=recordingAudioFile;
    fileEncodingQueue.postRunnable(() -> {
      stopRecord();
      AndroidUtilities.runOnUIThread(() -> {
        audioToSend.date=ConnectionsManager.getInstance(recordingCurrentAccount).getCurrentTime();
        audioToSend.size=(int)recordingAudioFileToSend.length();
        TLRPC.TL_documentAttributeAudio attributeAudio=new TLRPC.TL_documentAttributeAudio();
        attributeAudio.voice=true;
        attributeAudio.waveform=getWaveform2(recordSamples,recordSamples.length);
        if (attributeAudio.waveform != null) {
          attributeAudio.flags|=4;
        }
        long duration=recordTimeCount;
        attributeAudio.duration=(int)(recordTimeCount / 1000);
        audioToSend.attributes.add(attributeAudio);
        if (duration > 700) {
          if (send == 1) {
            SendMessagesHelper.getInstance(recordingCurrentAccount).sendMessage(audioToSend,null,recordingAudioFileToSend.getAbsolutePath(),recordDialogId,recordReplyingMessageObject,null,null,null,null,0,null);
          }
          NotificationCenter.getInstance(recordingCurrentAccount).postNotificationName(NotificationCenter.audioDidSent,send == 2 ? audioToSend : null,send == 2 ? recordingAudioFileToSend.getAbsolutePath() : null);
        }
 else {
          NotificationCenter.getInstance(recordingCurrentAccount).postNotificationName(NotificationCenter.audioRecordTooShort,false);
          recordingAudioFileToSend.delete();
        }
      }
);
    }
);
  }
  try {
    if (audioRecorder != null) {
      audioRecorder.release();
      audioRecorder=null;
    }
  }
 catch (  Exception e) {
    FileLog.e(e);
  }
  recordingAudio=null;
  recordingAudioFile=null;
}
