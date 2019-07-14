/** 
 * Prepares the intent to launch the ringtone picker. This can be modified to adjust the parameters of the ringtone picker.
 * @param ringtonePickerIntent The ringtone picker intent that can bemodified by putting extras.
 */
protected void onPrepareRingtonePickerIntent(Intent ringtonePickerIntent){
  ringtonePickerIntent.putExtra(RingtoneManager.EXTRA_RINGTONE_EXISTING_URI,getRingtoneUri());
  ringtonePickerIntent.putExtra(RingtoneManager.EXTRA_RINGTONE_SHOW_DEFAULT,mShowDefault);
  if (mShowDefault) {
    ringtonePickerIntent.putExtra(RingtoneManager.EXTRA_RINGTONE_DEFAULT_URI,RingtoneManager.getDefaultUri(getRingtoneType()));
  }
  ringtonePickerIntent.putExtra(RingtoneManager.EXTRA_RINGTONE_SHOW_SILENT,mShowSilent);
  ringtonePickerIntent.putExtra(RingtoneManager.EXTRA_RINGTONE_TYPE,mRingtoneType);
  ringtonePickerIntent.putExtra(RingtoneManager.EXTRA_RINGTONE_TITLE,getTitle());
}
