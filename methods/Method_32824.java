public void removeFinishedSession(TerminalSession finishedSession){
  TermuxService service=mTermService;
  int index=service.removeTermSession(finishedSession);
  mListViewAdapter.notifyDataSetChanged();
  if (mTermService.getSessions().isEmpty()) {
    finish();
  }
 else {
    if (index >= service.getSessions().size()) {
      index=service.getSessions().size() - 1;
    }
    switchToSession(service.getSessions().get(index));
  }
}
