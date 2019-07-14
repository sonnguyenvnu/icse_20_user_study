public Intent makeRingtonePickerIntent(){
  Intent intent=new Intent(RingtoneManager.ACTION_RINGTONE_PICKER);
  onPrepareRingtonePickerIntent(intent);
  return intent;
}
