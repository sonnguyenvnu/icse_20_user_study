/** 
 * only for springwebsocket
 * @return
 */
public char[] toCharArrayForSpringWebSocket(){
  if (this.writer != null) {
    throw new UnsupportedOperationException("writer not null");
  }
  char[] newValue=new char[count - 2];
  System.arraycopy(buf,1,newValue,0,count - 2);
  return newValue;
}
