/** 
 * Get a license by name
 * @param license license name
 * @return License
 * @throws java.lang.IllegalStateException when unknown license is requested
 */
public static License read(final String license){
  final String trimmedLicense=license.trim();
  if (sLicenses.containsKey(trimmedLicense)) {
    return sLicenses.get(trimmedLicense);
  }
 else {
    throw new IllegalStateException(String.format("no such license available: %s, did you forget to register it?",trimmedLicense));
  }
}
