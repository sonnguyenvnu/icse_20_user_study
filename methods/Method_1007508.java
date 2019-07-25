/** 
 * Send an error page to the client.
 * @param resp The response object. 
 * @param statusCode See HttpResponse.ST*  
 * @param htmlMsg  The body of the message that gives more detail. 
 * @throws IOException
 * @throws Pausable
 */
public void problem(HttpResponse resp,byte[] statusCode,String htmlMsg) throws IOException, Pausable {
  resp.status=statusCode;
  resp.setContentType("text/html");
  OutputStream os=resp.getOutputStream();
  os.write(pre);
  os.write(htmlMsg.getBytes());
  os.write(post);
  sendResponse(resp);
}
