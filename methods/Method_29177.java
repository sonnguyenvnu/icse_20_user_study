public String getNextFireDate(){
  if (nextFireTime > 0) {
    return DateUtil.formatYYYYMMddHHMMSS(new Date(nextFireTime));
  }
  return "";
}
