public static Date getDate(String dateString,String format) throws TwitterException {
  LinkedBlockingQueue<SimpleDateFormat> simpleDateFormats=formatMapQueue.get(format);
  if (simpleDateFormats == null) {
    simpleDateFormats=new LinkedBlockingQueue<SimpleDateFormat>();
    formatMapQueue.put(format,simpleDateFormats);
  }
  SimpleDateFormat sdf=simpleDateFormats.poll();
  if (null == sdf) {
    sdf=new SimpleDateFormat(format,Locale.US);
    sdf.setTimeZone(java.util.TimeZone.getTimeZone("UTC"));
  }
  try {
    return sdf.parse(dateString);
  }
 catch (  ParseException pe) {
    throw new TwitterException("Unexpected date format(" + dateString + ") returned from twitter.com",pe);
  }
 finally {
    try {
      simpleDateFormats.put(sdf);
    }
 catch (    InterruptedException ignore) {
    }
  }
}
