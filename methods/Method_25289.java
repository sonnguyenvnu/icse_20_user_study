/** 
 * Loads the Error Prone version. <p>This depends on the Maven build, and will always return  {@code Optional.absent()} with otherbuild systems.
 */
public static Optional<String> loadVersionFromPom(){
  try (InputStream stream=ErrorProneVersion.class.getResourceAsStream(PROPERTIES_RESOURCE)){
    if (stream == null) {
      return Optional.absent();
    }
    Properties mavenProperties=new Properties();
    mavenProperties.load(stream);
    return Optional.of(mavenProperties.getProperty("version"));
  }
 catch (  IOException expected) {
    return Optional.absent();
  }
}
