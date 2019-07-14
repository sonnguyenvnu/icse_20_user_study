public static String getJvmConfig(){
  StringBuilder buffer=new StringBuilder();
  append(buffer,RESOURCE,"jvm.mem");
  append(buffer,RESOURCE,"jvm.log");
  append(buffer,RESOURCE,"jvm.gc");
  append(buffer,RESOURCE,"jvm.others");
  append(buffer,RESOURCE,"jvm.args");
  return buffer.toString();
}
