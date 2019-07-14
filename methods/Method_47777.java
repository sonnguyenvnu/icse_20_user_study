private void drawErrorWidget(Context context,AppWidgetManager manager,int widgetId,RuntimeException e){
  RemoteViews errorView=new RemoteViews(context.getPackageName(),R.layout.widget_error);
  if (e instanceof HabitNotFoundException) {
    errorView.setCharSequence(R.id.label,"setText",context.getString(R.string.habit_not_found));
  }
  manager.updateAppWidget(widgetId,errorView);
}
