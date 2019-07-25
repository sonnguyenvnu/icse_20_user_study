/** 
 * make GET request
 * @param source_url
 * @return the response 
 * @throws IOException
 */
public static byte[] load(String source_url) throws IOException {
  ClientConnection connection=new ClientConnection(source_url);
  return connection.load();
}
