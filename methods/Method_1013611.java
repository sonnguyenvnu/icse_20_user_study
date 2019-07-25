/** 
 * Resets the ToolIO and deletes the messages    the mode and user directory are not changed 
 */
public static synchronized void reset(){
  messages=new String[InitialMaxLength];
  length=0;
  nextMessage="";
}
