void addNewSession(boolean failSafe,String sessionName){
  if (mTermService.getSessions().size() >= MAX_SESSIONS) {
    new AlertDialog.Builder(this).setTitle(R.string.max_terminals_reached_title).setMessage(R.string.max_terminals_reached_message).setPositiveButton(android.R.string.ok,null).show();
  }
 else {
    TerminalSession newSession=mTermService.createTermSession(null,null,null,failSafe);
    if (sessionName != null) {
      newSession.mSessionName=sessionName;
    }
    switchToSession(newSession);
    getDrawer().closeDrawers();
  }
}
