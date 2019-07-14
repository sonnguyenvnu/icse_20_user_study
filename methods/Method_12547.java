@Nullable private static Instant getInstant(Object o){
  try {
    if (o instanceof String) {
      return OffsetDateTime.parse((String)o,TIMESTAMP_PATTERN).toInstant();
    }
 else     if (o instanceof Long) {
      return Instant.ofEpochMilli((Long)o);
    }
  }
 catch (  DateTimeException|ClassCastException e) {
    return null;
  }
  return null;
}
