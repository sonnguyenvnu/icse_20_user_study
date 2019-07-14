/** 
 * Non-fatal error message with optional stack trace side dish.
 */
static public void showWarningTiered(String title,String primary,String secondary,Throwable e){
  if (title == null)   title="Warning";
  final String message=primary + "\n" + secondary;
  if (Base.isCommandLine()) {
    System.out.println(title + ": " + message);
  }
 else {
    if (!Platform.isMacOS()) {
      JOptionPane.showMessageDialog(new JFrame(),"<html><body>" + "<b>" + primary + "</b>" + "<br>" + secondary,title,JOptionPane.WARNING_MESSAGE);
    }
 else {
      JOptionPane pane=new JOptionPane("<html> " + "<head> <style type=\"text/css\">" + "b { font: 13pt \"Lucida Grande\" }" + "p { font: 11pt \"Lucida Grande\"; margin-top: 8px; width: 300px }" + "</style> </head>" + "<b>" + primary + "</b>" + "<p>" + secondary + "</p>",JOptionPane.WARNING_MESSAGE);
      JDialog dialog=pane.createDialog(new JFrame(),null);
      dialog.setVisible(true);
    }
  }
  if (e != null)   e.printStackTrace();
}
