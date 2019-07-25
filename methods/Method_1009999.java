public void logout(){
  String login=getAuthLogin();
  if (login == null) {
    return;
  }
  Set<String> logins=getPrefs().getStringSet(KEY_ALL_LOGINS,null);
  logins.remove(login);
  getPrefs().edit().putString(KEY_ACTIVE_LOGIN,logins.size() > 0 ? logins.iterator().next() : null).putStringSet(KEY_ALL_LOGINS,logins).remove(KEY_PREFIX_TOKEN + login).remove(KEY_PREFIX_USER_ID + login).apply();
  NotificationsJob.cancelJob();
}
