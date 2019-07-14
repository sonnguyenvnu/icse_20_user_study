@Override public void showEditHistoryScreen(){
  HistoryEditorDialog dialog=new HistoryEditorDialog();
  dialog.setHabit(habit);
  dialog.setController(this);
  dialog.show(activity.getSupportFragmentManager(),"historyEditor");
}
