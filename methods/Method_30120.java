@Override public void onResourceReady(@NonNull Bitmap resource,@Nullable Transition<? super Bitmap> transition){
  remoteViews.setImageViewBitmap(viewId,resource);
  AppWidgetManager appWidgetManager=AppWidgetManager.getInstance(context);
  if (componentName != null) {
    appWidgetManager.updateAppWidget(componentName,remoteViews);
  }
 else {
    appWidgetManager.updateAppWidget(widgetIds,remoteViews);
  }
  clear();
}
