private static boolean get(Configuration cfg,String hadoop2,String hadoop1,boolean defaultValue){
  String result=get(cfg,hadoop2,hadoop1);
  if ("true".equals(result))   return true;
 else   if ("false".equals(result))   return false;
 else   return defaultValue;
}
