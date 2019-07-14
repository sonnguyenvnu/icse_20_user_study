public static int getIntAttribute(@NonNull Context context,@NonNull AttributeSet attrs,@NonNull String name,int defaultValue){
  String number=getAttribute(context,attrs,name,null);
  if (number != null)   return Integer.parseInt(number);
 else   return defaultValue;
}
