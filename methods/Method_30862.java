/** 
 * Should behave the same as  {@link TimeUtils#formatDoubanDateTime(String,Context)}.
 */
public void setDoubanTime(String doubanTime){
  try {
    setTime(TimeUtils.parseDoubanDateTime(doubanTime));
  }
 catch (  DateTimeParseException e) {
    LogUtils.e("Unable to parse date time: " + doubanTime);
    e.printStackTrace();
    setText(doubanTime);
  }
}
