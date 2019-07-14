public static Timestamp strToTimestamp(Date date){
  return Timestamp.valueOf(formatTimestamp.format(date));
}
