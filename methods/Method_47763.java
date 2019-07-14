@Nullable public static String getAttribute(@NonNull Context context,@NonNull AttributeSet attrs,@NonNull String name,@Nullable String defaultValue){
  int resId=attrs.getAttributeResourceValue(ISORON_NAMESPACE,name,0);
  if (resId != 0)   return context.getResources().getString(resId);
  String value=attrs.getAttributeValue(ISORON_NAMESPACE,name);
  if (value != null)   return value;
 else   return defaultValue;
}
