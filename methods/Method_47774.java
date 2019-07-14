@Override public void onAppWidgetOptionsChanged(@Nullable Context context,@Nullable AppWidgetManager manager,int widgetId,@Nullable Bundle options){
  try {
    if (context == null)     throw new RuntimeException("context is null");
    if (manager == null)     throw new RuntimeException("manager is null");
    if (options == null)     throw new RuntimeException("options is null");
    context.setTheme(R.style.OpaqueWidgetTheme);
    updateDependencies(context);
    BaseWidget widget=getWidgetFromId(context,widgetId);
    WidgetDimensions dims=getDimensionsFromOptions(context,options);
    widget.setDimensions(dims);
    updateAppWidget(manager,widget);
  }
 catch (  RuntimeException e) {
    drawErrorWidget(context,manager,widgetId,e);
    e.printStackTrace();
  }
}
