/** 
 * Import settings from given file. New settings (with the same ID/key) override existing once.
 * @param settingsFile File
 * @throws IllegalArgumentException In case the import of the file fails
 */
private void add(final File settingsFile) throws IllegalArgumentException {
  Objects.requireNonNull(settingsFile);
  if (!(settingsFile.isFile() && settingsFile.canRead())) {
    String msg=String.format("Settings file '%s' does not exist or can not be read.",settingsFile);
    throw new IllegalArgumentException(msg);
  }
  try {
    Properties newSettings=FileParser.parse(settingsFile);
    properties.putAll(newSettings);
  }
 catch (  IOException|IllegalArgumentException|NullPointerException exception) {
    String message=String.format("Failed to add properties from '%s' to formatter settings.",settingsFile);
    String detailedMessage=exception.getMessage();
    if (null != detailedMessage) {
      message+=String.format(" %s",detailedMessage);
    }
    throw new IllegalArgumentException(message,exception);
  }
}
