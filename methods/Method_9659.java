@Override public void onAudioSettingsChanged(){
  VoIPBaseService svc=VoIPBaseService.getSharedInstance();
  if (svc == null)   return;
  micToggle.setChecked(svc.isMicMute());
  if (!svc.hasEarpiece() && !svc.isBluetoothHeadsetConnected()) {
    spkToggle.setVisibility(View.INVISIBLE);
  }
 else {
    spkToggle.setVisibility(View.VISIBLE);
    if (!svc.hasEarpiece()) {
      spkToggle.setImageResource(R.drawable.ic_bluetooth_white_24dp);
      spkToggle.setChecked(svc.isSpeakerphoneOn());
    }
 else     if (svc.isBluetoothHeadsetConnected()) {
switch (svc.getCurrentAudioRoute()) {
case VoIPBaseService.AUDIO_ROUTE_BLUETOOTH:
        spkToggle.setImageResource(R.drawable.ic_bluetooth_white_24dp);
      break;
case VoIPBaseService.AUDIO_ROUTE_SPEAKER:
    spkToggle.setImageResource(R.drawable.ic_volume_up_white_24dp);
  break;
case VoIPBaseService.AUDIO_ROUTE_EARPIECE:
spkToggle.setImageResource(R.drawable.ic_phone_in_talk_white_24dp);
break;
}
spkToggle.setChecked(false);
}
 else {
spkToggle.setImageResource(R.drawable.ic_volume_up_white_24dp);
spkToggle.setChecked(svc.isSpeakerphoneOn());
}
}
}
