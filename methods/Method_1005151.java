public static Timestamp gettimestamp(){
  Date dt=new Date();
  DateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
  String nowTime=df.format(dt);
  java.sql.Timestamp buydate=java.sql.Timestamp.valueOf(nowTime);
  return buydate;
}
