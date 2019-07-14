public static Long toMillisNullSafe(Date time){
  return time == null ? null : time.getTime();
}
