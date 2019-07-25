private static void err(String title,String msg){
  System.err.println(msg);
  JOptionPane.showMessageDialog(null,msg,title,JOptionPane.ERROR_MESSAGE);
}
