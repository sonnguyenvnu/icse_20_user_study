public void onDataSetChanged(){
  Log.i("StackRemoteViewsFactory","onDataSetChanged started");
  HabitsApplication app=(HabitsApplication)context.getApplicationContext();
  HabitList habitList=app.getComponent().getHabitList();
  Bundle options=AppWidgetManager.getInstance(context).getAppWidgetOptions(widgetId);
  ArrayList<RemoteViews> newRemoteViews=new ArrayList<>();
  if (Looper.myLooper() == null)   Looper.prepare();
  for (  long id : habitIds) {
    Habit h=habitList.getById(id);
    if (h == null)     throw new HabitNotFoundException();
    BaseWidget widget=constructWidget(h);
    widget.setDimensions(getDimensionsFromOptions(context,options));
    RemoteViews landscapeViews=widget.getLandscapeRemoteViews();
    RemoteViews portraitViews=widget.getPortraitRemoteViews();
    newRemoteViews.add(new RemoteViews(landscapeViews,portraitViews));
    Log.i("StackRemoteViewsFactory","onDataSetChanged constructed widget " + id);
  }
  remoteViews=newRemoteViews;
  Log.i("StackRemoteViewsFactory","onDataSetChanged ended");
}
