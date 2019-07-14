/** 
 * @param name name
 * @param body media body as stream
 * @since Twitter4J 2.2.5
 */
public void setMedia(String name,InputStream body){
  this.mediaName=name;
  this.mediaBody=body;
}
