/** 
 * Creates an  {@link ExposedPort} for {@link InternetProtocol#UDP}. This is a shortcut for <code>new ExposedPort(port,  {@link InternetProtocol#UDP})</code>
 */
public static ExposedPort udp(int port){
  return new ExposedPort(port,UDP);
}
