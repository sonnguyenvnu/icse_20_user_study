/** 
 * ???1?????????"??" ???1??????????"XX????" ???1????????"XX????" ??????1???????“?-?”???“05-03” ?????????“?-?-?”???“2014-03-11”
 * @param time
 * @return
 */
public static String translateTime(String time){
  Date nowTime=new Date();
  String currDate=sdf1.format(nowTime);
  long currentMilliseconds=nowTime.getTime();
  Date date=null;
  try {
    date=sdf1.parse(time);
  }
 catch (  Exception e) {
    e.printStackTrace();
    return time;
  }
  if (date != null) {
    timeMilliseconds=date.getTime();
  }
  long timeDifferent=currentMilliseconds - timeMilliseconds;
  if (timeDifferent < 60000) {
    return "??";
  }
  if (timeDifferent < 3600000) {
    long longMinute=timeDifferent / 60000;
    int minute=(int)(longMinute % 100);
    return minute + "????";
  }
  long l=24 * 60 * 60 * 1000;
  if (timeDifferent < l) {
    long longHour=timeDifferent / 3600000;
    int hour=(int)(longHour % 100);
    return hour + "????";
  }
  if (timeDifferent >= l) {
    String currYear=currDate.substring(0,4);
    String year=time.substring(0,4);
    if (!year.equals(currYear)) {
      return time.substring(0,10);
    }
    return time.substring(5,10);
  }
  return time;
}
