@OnItemSelected(R.id.spinner) public void onItemSelected(int position){
  setBucketSizeFromPosition(position);
  HabitsApplication app=(HabitsApplication)getContext().getApplicationContext();
  app.getComponent().getWidgetUpdater().updateWidgets();
  refreshData();
}
