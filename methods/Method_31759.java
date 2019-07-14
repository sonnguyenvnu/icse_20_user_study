private void addMenus(JMenuBar menuBar){
  JMenu fileMenu=new JMenu("File");
  JMenu viewMenu=new JMenu("View");
  menuBar.add(fileMenu);
  menuBar.add(viewMenu);
  Action open=new OpenAction();
  JMenuItem jmiOpen=new JMenuItem(open);
  Action exit=new ExitAction();
  JMenuItem jmiExit=new JMenuItem(exit);
  Action getter=new GetterAction();
  jmiGetter=new JMenuItem(getter);
  getter.setEnabled(true);
  Action hex=new HexAction();
  jmiHex=new JMenuItem(hex);
  hex.setEnabled(true);
  Action date=new DateAction();
  jmiDate=new JMenuItem(date);
  date.setEnabled(true);
  Action cal=new CalAction();
  jmiCal=new JMenuItem(cal);
  cal.setEnabled(true);
  fileMenu.add(jmiOpen);
  fileMenu.addSeparator();
  fileMenu.add(jmiExit);
  viewMenu.add(jmiGetter);
  viewMenu.add(jmiHex);
  viewMenu.add(jmiDate);
  viewMenu.add(jmiCal);
}
