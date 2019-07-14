public String getPrevFireDate(){
  if (prevFireTime > 0) {
    return DateUtil.formatYYYYMMddHHMMSS(new Date(prevFireTime));
  }
  return "";
}
