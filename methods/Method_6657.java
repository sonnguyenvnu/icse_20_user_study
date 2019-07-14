private void setUseFrontSpeaker(boolean value){
  useFrontSpeaker=value;
  AudioManager audioManager=NotificationsController.audioManager;
  if (useFrontSpeaker) {
    audioManager.setBluetoothScoOn(false);
    audioManager.setSpeakerphoneOn(false);
  }
 else {
    audioManager.setSpeakerphoneOn(true);
  }
}
