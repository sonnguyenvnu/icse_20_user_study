@Override public void reattachDialogs(){
  super.reattachDialogs();
  HistoryEditorDialog historyEditor=(HistoryEditorDialog)activity.getSupportFragmentManager().findFragmentByTag("historyEditor");
  if (historyEditor != null)   historyEditor.setController(this);
}
