/** 
 * ??HTTP???
 * @param serverProperties serverProperties
 * @return int
 */
public static int serverPort(ServerProperties serverProperties){
  return Objects.isNull(serverProperties) ? 0 : (Objects.isNull(serverProperties.getPort()) ? 8080 : serverProperties.getPort());
}
