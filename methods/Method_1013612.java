/** 
 * Prints a string in to the ToolIO buffer in a separate line
 * @param str String to be printed
 */
public void println(String str){
synchronized (ToolPrintStream.class) {
    String thisMessage=ToolIO.nextMessage + str;
    ToolIO.nextMessage="";
    if (ToolIO.messages.length == ToolIO.length) {
      String[] newMessages=new String[2 * ToolIO.messages.length];
      System.arraycopy(ToolIO.messages,0,newMessages,0,ToolIO.messages.length);
      ToolIO.messages=newMessages;
    }
    ToolIO.messages[ToolIO.length]=thisMessage;
    ToolIO.length++;
    ToolPrintStream.class.notifyAll();
  }
}
