public static Date getSimpleDateFormatDate(String value,String format){
  if (!StringUtils.isEmpty(value)) {
    Date date=null;
    if (!StringUtils.isEmpty(format)) {
      SimpleDateFormat simpleDateFormat=new SimpleDateFormat(format);
      try {
        date=simpleDateFormat.parse(value);
        return date;
      }
 catch (      ParseException e) {
      }
    }
    for (    String dateFormat : DATE_FORMAT_LIST) {
      try {
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat(dateFormat);
        date=simpleDateFormat.parse(value);
      }
 catch (      ParseException e) {
      }
      if (date != null) {
        break;
      }
    }
    return date;
  }
  return null;
}
