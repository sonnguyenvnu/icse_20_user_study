/** 
 * Creates an  {@link ExposedPort} for {@link InternetProtocol#TCP}. This is a shortcut for <code>new ExposedPort(port,  {@link InternetProtocol#TCP})</code>
 */
public static ExposedPort tcp(int port){
  return new ExposedPort(port,TCP);
}
