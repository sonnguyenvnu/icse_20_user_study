@Contract("_,_,_,!null -> !null") public static Integer getColorAttribute(@NonNull Context context,@NonNull AttributeSet attrs,@NonNull String name,@Nullable Integer defaultValue){
  int resId=attrs.getAttributeResourceValue(ISORON_NAMESPACE,name,0);
  if (resId != 0)   return context.getResources().getColor(resId);
 else   return defaultValue;
}
