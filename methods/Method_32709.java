/** 
 * Returns  {@link DateTimeZone#UTC UTC} for <code>"UTC"</code>, nullotherwise.
 */
public DateTimeZone getZone(String id){
  if ("UTC".equalsIgnoreCase(id)) {
    return DateTimeZone.UTC;
  }
  return null;
}
