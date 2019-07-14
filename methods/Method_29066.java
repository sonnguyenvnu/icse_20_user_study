public static String getRun(){
  StringBuilder buffer=new StringBuilder();
  buffer.append("nohup java ");
  buffer.append(String.format("`java -jar %s -jvm`",getPackageName()));
  buffer.append(" -jar ");
  buffer.append(getPackageName());
  buffer.append(" > ");
  buffer.append("./console.log");
  buffer.append(" 2>&1 &");
  return buffer.toString();
}
