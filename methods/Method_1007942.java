/** 
 * Create a  {@link DockerHost} from an explicit address or uri.
 * @param endpoint The Docker endpoint.
 * @param certPath The certificate path.
 * @return The DockerHost object.
 */
public static DockerHost from(final String endpoint,final String certPath){
  return new DockerHost(endpoint,certPath);
}
