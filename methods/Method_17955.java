private static boolean hasSameRootContext(Context context1,Context context2){
  return ContextUtils.getRootContext(context1) == ContextUtils.getRootContext(context2);
}
