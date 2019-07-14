public static boolean validate(final Object value,final LocalDateTime then){
  if (value == null) {
    return true;
  }
  LocalDateTimeConverter ldtc=(LocalDateTimeConverter)TypeConverterManager.get().lookup(LocalDateTime.class);
  LocalDateTime now=ldtc.convert(value);
  return now.isAfter(then);
}
