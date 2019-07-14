/** 
 * Non-fatal error message with optional stack trace side dish.
 */
static public void showWarning(String title,String message,Throwable e){
  if (title == null)   title="Warning";
  if (Base.isCommandLine()) {
    System.out.println(title + ": " + message);
  }
 else {
    JOptionPane.showMessageDialog(new Frame(),message,title,JOptionPane.WARNING_MESSAGE);
  }
  if (e != null)   e.printStackTrace();
}
