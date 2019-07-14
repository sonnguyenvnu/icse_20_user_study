/** 
 * Gets the default time zone. <p> The default time zone is derived from the system property  {@code user.timezone}. If that is  {@code null} or is not a valid identifier, then the value of theJDK  {@code TimeZone} default is converted. If that fails, {@code UTC} is used.<p> NOTE: If the  {@code java.util.TimeZone} default is updated <i>after</i> calling thismethod, then the change will not be picked up here.
 * @return the default datetime zone object
 */
public static DateTimeZone getDefault(){
  DateTimeZone zone=cDefault.get();
  if (zone == null) {
    try {
      try {
        String id=System.getProperty("user.timezone");
        if (id != null) {
          zone=forID(id);
        }
      }
 catch (      RuntimeException ex) {
      }
      if (zone == null) {
        zone=forTimeZone(TimeZone.getDefault());
      }
    }
 catch (    IllegalArgumentException ex) {
    }
    if (zone == null) {
      zone=UTC;
    }
    if (!cDefault.compareAndSet(null,zone)) {
      zone=cDefault.get();
    }
  }
  return zone;
}
