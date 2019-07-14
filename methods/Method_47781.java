public RemoteViews getLoadingView(){
  Bundle options=AppWidgetManager.getInstance(context).getAppWidgetOptions(widgetId);
  EmptyWidget widget=new EmptyWidget(context,widgetId);
  widget.setDimensions(getDimensionsFromOptions(context,options));
  RemoteViews landscapeViews=widget.getLandscapeRemoteViews();
  RemoteViews portraitViews=widget.getPortraitRemoteViews();
  return new RemoteViews(landscapeViews,portraitViews);
}
