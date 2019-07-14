public String getEndDate(){
  if (endTime > 0) {
    return DateUtil.formatYYYYMMddHHMMSS(new Date(endTime));
  }
  return "";
}
