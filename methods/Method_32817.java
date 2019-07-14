String toToastTitle(TerminalSession session){
  final int indexOfSession=mTermService.getSessions().indexOf(session);
  StringBuilder toastTitle=new StringBuilder("[" + (indexOfSession + 1) + "]");
  if (!TextUtils.isEmpty(session.mSessionName)) {
    toastTitle.append(" ").append(session.mSessionName);
  }
  String title=session.getTitle();
  if (!TextUtils.isEmpty(title)) {
    toastTitle.append(session.mSessionName == null ? " " : "\n");
    toastTitle.append(title);
  }
  return toastTitle.toString();
}
