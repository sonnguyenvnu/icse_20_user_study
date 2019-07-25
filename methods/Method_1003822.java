/** 
 * Creates a new builder for creating Retrofit clients.
 * @param endpoint the endpoint for client implementations.
 * @return a client builder
 */
public static Builder client(URI endpoint){
  return new Builder(endpoint);
}
