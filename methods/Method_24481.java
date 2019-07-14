/** 
 * Write a command on one line (as a String), then start a new line and write out a formatted float. Available for anyone who wants to insert additional commands into the DXF stream.
 */
public void write(String cmd,float val){
  writer.println(cmd);
  writer.println(val);
}
