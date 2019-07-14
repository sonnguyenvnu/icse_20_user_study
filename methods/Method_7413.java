@Override public void startRingtoneAndVibration(){
  if (!startedRinging) {
    startRingtoneAndVibration(user.id);
    startedRinging=true;
  }
}
