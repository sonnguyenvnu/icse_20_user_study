public RemoteViews getViewAt(int position){
  Log.i("StackRemoteViewsFactory","getViewAt " + position);
  if (position < 0 || position > remoteViews.size())   return null;
  return remoteViews.get(position);
}
