@Override public void onSoundSelected(Uri uri){
  PrefGetter.setNotificationSound(uri);
  if (notificationSoundPath != null && notificationSoundPath.isVisible())   notificationSoundPath.setSummary(FileHelper.getRingtoneName(getContext(),uri));
}
