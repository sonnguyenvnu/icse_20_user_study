private void registerAudioBecomingNoisyReceiver(){
  if (mAudioBecomingNoisyReceiverRegistered) {
    return;
  }
  mContext.registerReceiver(mAudioBecomingNoisyReceiver,mAudioBecomingNoisyIntentFilter);
  mAudioBecomingNoisyReceiverRegistered=true;
}
