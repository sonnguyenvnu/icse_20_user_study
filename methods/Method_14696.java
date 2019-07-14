static public void safeInc(ObjectNode obj,String key){
  int currentValue=getInt(obj,key,0);
  safePut(obj,key,currentValue + 1);
}
