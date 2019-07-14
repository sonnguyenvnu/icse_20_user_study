public boolean isExpire(){
  if (expiresIn == null) {
    return true;
  }
  if (expiresIn <= 0) {
    return false;
  }
  long time=updateTime == null ? createTime : updateTime;
  return System.currentTimeMillis() - time > expiresIn * 1000;
}
