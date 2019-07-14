public static StatusInfo valueOf(String statusCode,@Nullable Map<String,?> details){
  return new StatusInfo(statusCode,details);
}
