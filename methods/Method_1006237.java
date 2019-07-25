public String now(){
  String timeStampFormat=timestampFormat;
  return DateTimeFormatter.ofPattern(timeStampFormat).format(LocalDateTime.now());
}
