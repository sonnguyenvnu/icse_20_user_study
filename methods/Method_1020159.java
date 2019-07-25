/** 
 * ????
 */
public void execute(){
  handleProcess();
  if (sucessor != null) {
    sucessor.execute();
  }
}
