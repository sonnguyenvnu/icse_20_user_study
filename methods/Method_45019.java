/** 
 * Method allows for users to copy the stacktrace for reporting any issues. Add Cool Hyperlink Enhanced for mouse users.
 * @param message
 * @param e
 */
public static void showExceptionDialog(String message,Exception e){
  StringWriter sw=new StringWriter();
  PrintWriter pw=new PrintWriter(sw);
  e.printStackTrace(pw);
  String stacktrace=sw.toString();
  try {
    sw.close();
    pw.close();
  }
 catch (  IOException e1) {
    e1.printStackTrace();
  }
  System.out.println(stacktrace);
  JPanel pane=new JPanel();
  pane.setLayout(new BoxLayout(pane,BoxLayout.PAGE_AXIS));
  if (message.contains("\n")) {
    for (    String s : message.split("\n")) {
      pane.add(new JLabel(s));
    }
  }
 else {
    pane.add(new JLabel(message));
  }
  pane.add(new JLabel(" \n"));
  final JTextArea exception=new JTextArea(25,100);
  exception.setFont(new Font(Font.SANS_SERIF,Font.PLAIN,10));
  exception.setText(stacktrace);
  exception.addMouseListener(new MouseAdapter(){
    @Override public void mouseClicked(    MouseEvent e){
      if (SwingUtilities.isRightMouseButton(e)) {
        new JPopupMenu(){
{
            JMenuItem menuitem=new JMenuItem("Select All");
            menuitem.addActionListener(new ActionListener(){
              @Override public void actionPerformed(              ActionEvent e){
                exception.requestFocus();
                exception.selectAll();
              }
            }
);
            this.add(menuitem);
            menuitem=new JMenuItem("Copy");
            menuitem.addActionListener(new DefaultEditorKit.CopyAction());
            this.add(menuitem);
          }
        }
.show(e.getComponent(),e.getX(),e.getY());
      }
    }
  }
);
  JScrollPane scroll=new JScrollPane(exception);
  scroll.setBorder(new CompoundBorder(BorderFactory.createTitledBorder("Stacktrace"),new BevelBorder(BevelBorder.LOWERED)));
  pane.add(scroll);
  final String issue="https://github.com/deathmarine/Luyten/issues";
  final JLabel link=new JLabel("<HTML>Submit to <FONT color=\"#000099\"><U>" + issue + "</U></FONT></HTML>");
  link.setCursor(new Cursor(Cursor.HAND_CURSOR));
  link.addMouseListener(new MouseAdapter(){
    @Override public void mouseClicked(    MouseEvent e){
      try {
        Desktop.getDesktop().browse(new URI(issue));
      }
 catch (      Exception e1) {
        e1.printStackTrace();
      }
    }
    @Override public void mouseEntered(    MouseEvent e){
      link.setText("<HTML>Submit to <FONT color=\"#00aa99\"><U>" + issue + "</U></FONT></HTML>");
    }
    @Override public void mouseExited(    MouseEvent e){
      link.setText("<HTML>Submit to <FONT color=\"#000099\"><U>" + issue + "</U></FONT></HTML>");
    }
  }
);
  pane.add(link);
  JOptionPane.showMessageDialog(null,pane,"Error!",JOptionPane.ERROR_MESSAGE);
}
