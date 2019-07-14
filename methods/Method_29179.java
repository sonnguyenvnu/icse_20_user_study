public String getStartDate(){
  if (startTime > 0) {
    return DateUtil.formatYYYYMMddHHMMSS(new Date(startTime));
  }
  return "";
}
