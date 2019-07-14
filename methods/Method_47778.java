@NonNull public WidgetDimensions getDimensionsFromOptions(@NonNull Context ctx,@NonNull Bundle options){
  int maxWidth=(int)dpToPixels(ctx,options.getInt(OPTION_APPWIDGET_MAX_WIDTH));
  int maxHeight=(int)dpToPixels(ctx,options.getInt(OPTION_APPWIDGET_MAX_HEIGHT));
  int minWidth=(int)dpToPixels(ctx,options.getInt(OPTION_APPWIDGET_MIN_WIDTH));
  int minHeight=(int)dpToPixels(ctx,options.getInt(OPTION_APPWIDGET_MIN_HEIGHT));
  return new WidgetDimensions(minWidth,maxHeight,maxWidth,minHeight);
}
