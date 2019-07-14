public void switchToSession(boolean forward){
  TerminalSession currentSession=getCurrentTermSession();
  int index=mTermService.getSessions().indexOf(currentSession);
  if (forward) {
    if (++index >= mTermService.getSessions().size())     index=0;
  }
 else {
    if (--index < 0)     index=mTermService.getSessions().size() - 1;
  }
  switchToSession(mTermService.getSessions().get(index));
}
