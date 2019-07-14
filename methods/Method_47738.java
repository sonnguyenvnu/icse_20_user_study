private void updateRingtoneDescription(){
  String ringtoneName=ringtoneManager.getName();
  if (ringtoneName == null)   return;
  Preference ringtonePreference=findPreference("reminderSound");
  ringtonePreference.setSummary(ringtoneName);
}
