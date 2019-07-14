/** 
 * Use  {@link me.zhanghai.android.douya.ui.TimeTextView} instead if the text is to be set on a{@code TextView}.
 */
public static String formatDoubanDateTime(String doubanDateTime,Context context){
  try {
    return formatDateTime(parseDoubanDateTime(doubanDateTime),context);
  }
 catch (  DateTimeParseException e) {
    LogUtils.e("Unable to parse date time: " + doubanDateTime);
    e.printStackTrace();
    return doubanDateTime;
  }
}
