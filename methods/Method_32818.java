void noteSessionInfo(){
  if (!mIsVisible)   return;
  TerminalSession session=getCurrentTermSession();
  final int indexOfSession=mTermService.getSessions().indexOf(session);
  showToast(toToastTitle(session),false);
  mListViewAdapter.notifyDataSetChanged();
  final ListView lv=findViewById(R.id.left_drawer_list);
  lv.setItemChecked(indexOfSession,true);
  lv.smoothScrollToPosition(indexOfSession);
}
