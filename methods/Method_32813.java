@SuppressLint("InflateParams") void renameSession(final TerminalSession sessionToRename){
  DialogUtils.textInput(this,R.string.session_rename_title,sessionToRename.mSessionName,R.string.session_rename_positive_button,text -> {
    sessionToRename.mSessionName=text;
    mListViewAdapter.notifyDataSetChanged();
  }
,-1,null,-1,null,null);
}
