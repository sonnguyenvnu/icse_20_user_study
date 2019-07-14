private static Long nullSafeUnixTime(Date time){
  return time != null ? DateUtils.toUnixTime(time) : null;
}
