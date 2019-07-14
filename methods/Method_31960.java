public DateTimeZone getZone(){
  Chronology base;
  if ((base=getBase()) != null) {
    return base.getZone();
  }
  return DateTimeZone.UTC;
}
