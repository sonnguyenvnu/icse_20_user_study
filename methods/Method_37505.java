/** 
 * Tests if the specified  {@link File} is older than the specified time reference.
 * @param file       the {@link File} of which the modification date must be compared.
 * @param timeMillis the time reference measured in milliseconds since theepoch (00:00:00 GMT, January 1, 1970)
 * @return {@code true} if the {@link File} exists and has been modified afterthe given time reference.
 */
public static boolean isOlder(final File file,final long timeMillis){
  return file.exists() && file.lastModified() < timeMillis;
}
