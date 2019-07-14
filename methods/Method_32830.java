static void storeCurrentSession(Context context,TerminalSession session){
  PreferenceManager.getDefaultSharedPreferences(context).edit().putString(TermuxPreferences.CURRENT_SESSION_KEY,session.mHandle).apply();
}
