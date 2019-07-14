public void toggleSpeakerphoneOrShowRouteSheet(Activity activity){
  if (isBluetoothHeadsetConnected() && hasEarpiece()) {
    BottomSheet.Builder bldr=new BottomSheet.Builder(activity).setItems(new CharSequence[]{LocaleController.getString("VoipAudioRoutingBluetooth",R.string.VoipAudioRoutingBluetooth),LocaleController.getString("VoipAudioRoutingEarpiece",R.string.VoipAudioRoutingEarpiece),LocaleController.getString("VoipAudioRoutingSpeaker",R.string.VoipAudioRoutingSpeaker)},new int[]{R.drawable.ic_bluetooth_white_24dp,R.drawable.ic_phone_in_talk_white_24dp,R.drawable.ic_volume_up_white_24dp},new DialogInterface.OnClickListener(){
      @Override public void onClick(      DialogInterface dialog,      int which){
        AudioManager am=(AudioManager)getSystemService(AUDIO_SERVICE);
        if (getSharedInstance() == null)         return;
        if (USE_CONNECTION_SERVICE && systemCallConnection != null) {
switch (which) {
case 0:
            systemCallConnection.setAudioRoute(CallAudioState.ROUTE_BLUETOOTH);
          break;
case 1:
        systemCallConnection.setAudioRoute(CallAudioState.ROUTE_WIRED_OR_EARPIECE);
      break;
case 2:
    systemCallConnection.setAudioRoute(CallAudioState.ROUTE_SPEAKER);
  break;
}
}
 else if (audioConfigured && !USE_CONNECTION_SERVICE) {
switch (which) {
case 0:
if (!bluetoothScoActive) {
  needSwitchToBluetoothAfterScoActivates=true;
  try {
    am.startBluetoothSco();
  }
 catch (  Throwable ignore) {
  }
}
 else {
  am.setBluetoothScoOn(true);
  am.setSpeakerphoneOn(false);
}
break;
case 1:
if (bluetoothScoActive) am.stopBluetoothSco();
am.setSpeakerphoneOn(false);
am.setBluetoothScoOn(false);
break;
case 2:
if (bluetoothScoActive) am.stopBluetoothSco();
am.setBluetoothScoOn(false);
am.setSpeakerphoneOn(true);
break;
}
updateOutputGainControlState();
}
 else {
switch (which) {
case 0:
audioRouteToSet=AUDIO_ROUTE_BLUETOOTH;
break;
case 1:
audioRouteToSet=AUDIO_ROUTE_EARPIECE;
break;
case 2:
audioRouteToSet=AUDIO_ROUTE_SPEAKER;
break;
}
}
for (StateListener l : stateListeners) l.onAudioSettingsChanged();
}
}
);
BottomSheet sheet=bldr.create();
sheet.setBackgroundColor(0xff2b2b2b);
sheet.show();
ViewGroup container=sheet.getSheetContainer();
for (int i=0; i < container.getChildCount(); i++) {
BottomSheet.BottomSheetCell cell=(BottomSheet.BottomSheetCell)container.getChildAt(i);
cell.setTextColor(0xFFFFFFFF);
}
return;
}
if (USE_CONNECTION_SERVICE && systemCallConnection != null && systemCallConnection.getCallAudioState() != null) {
if (hasEarpiece()) systemCallConnection.setAudioRoute(systemCallConnection.getCallAudioState().getRoute() == CallAudioState.ROUTE_SPEAKER ? CallAudioState.ROUTE_WIRED_OR_EARPIECE : CallAudioState.ROUTE_SPEAKER);
 else systemCallConnection.setAudioRoute(systemCallConnection.getCallAudioState().getRoute() == CallAudioState.ROUTE_BLUETOOTH ? CallAudioState.ROUTE_WIRED_OR_EARPIECE : CallAudioState.ROUTE_BLUETOOTH);
}
 else if (audioConfigured && !USE_CONNECTION_SERVICE) {
AudioManager am=(AudioManager)getSystemService(AUDIO_SERVICE);
if (hasEarpiece()) {
am.setSpeakerphoneOn(!am.isSpeakerphoneOn());
}
 else {
am.setBluetoothScoOn(!am.isBluetoothScoOn());
}
updateOutputGainControlState();
}
 else {
speakerphoneStateToSet=!speakerphoneStateToSet;
}
for (StateListener l : stateListeners) l.onAudioSettingsChanged();
}
