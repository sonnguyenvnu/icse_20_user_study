private static Date getDateTime(long collectTime) throws ParseException {
  try {
    return DateUtil.parseYYYYMMddHHMM(String.valueOf(collectTime));
  }
 catch (  Exception e) {
    return DateUtil.parseYYYYMMddHH(String.valueOf(collectTime));
  }
}
