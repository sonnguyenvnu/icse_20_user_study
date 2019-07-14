@OnCreateLayout public static Component onCreateLayout(ComponentContext c,@Prop String message,@Prop Throwable throwable){
  Log.e(TAG,message,throwable);
  return Column.create(c).backgroundColor(DARK_RED_FRAME).paddingDip(YogaEdge.ALL,1f).child(Text.create(c).backgroundColor(LIGHT_RED_BACKGROUND).paddingDip(YogaEdge.ALL,4f).textSizeDip(16f).text(message)).child(Text.create(c).backgroundColor(LIGHT_RED_BACKGROUND).paddingDip(YogaEdge.ALL,4f).textSizeDip(12f).textColor(LIGHT_GRAY_TEXT).typeface(Typeface.MONOSPACE).text(StacktraceHelper.formatStacktrace(throwable))).clickHandler(DebugErrorComponent.onClick(c)).build();
}
