private void buildHelpMenu(JMenu helpMenu){
  helpMenu.removeAll();
  JMenuItem menuItem=new JMenuItem("Legal");
  menuItem.addActionListener(new ActionListener(){
    @Override public void actionPerformed(    ActionEvent e){
      mainWindow.onLegalMenu();
    }
  }
);
  helpMenu.add(menuItem);
  JMenu menuDebug=new JMenu("Debug");
  menuItem=new JMenuItem("List JVM Classes");
  menuItem.addActionListener(new ActionListener(){
    @Override public void actionPerformed(    ActionEvent e){
      mainWindow.onListLoadedClasses();
    }
  }
);
  menuDebug.add(menuItem);
  helpMenu.add(menuDebug);
  menuItem=new JMenuItem("About");
  menuItem.addActionListener(new ActionListener(){
    @Override public void actionPerformed(    ActionEvent event){
      JPanel pane=new JPanel();
      pane.setLayout(new BoxLayout(pane,BoxLayout.PAGE_AXIS));
      JLabel title=new JLabel("Luyten " + Luyten.getVersion());
      title.setFont(new Font(Font.SANS_SERIF,Font.PLAIN,18));
      pane.add(title);
      pane.add(new JLabel("by Deathmarine"));
      String project="https://github.com/deathmarine/Luyten/";
      JLabel link=new JLabel("<HTML><FONT color=\"#000099\"><U>" + project + "</U></FONT></HTML>");
      link.setCursor(new Cursor(Cursor.HAND_CURSOR));
      link.addMouseListener(new LinkListener(project,link));
      pane.add(link);
      pane.add(new JLabel("Contributions By:"));
      pane.add(new JLabel("zerdei, toonetown, dstmath"));
      pane.add(new JLabel("virustotalop, xtrafrancyz,"));
      pane.add(new JLabel("mbax, quitten, mstrobel,"));
      pane.add(new JLabel("FisheyLP, and Syquel"));
      pane.add(new JLabel(" "));
      pane.add(new JLabel("Powered By:"));
      String procyon="https://bitbucket.org/mstrobel/procyon";
      link=new JLabel("<HTML><FONT color=\"#000099\"><U>" + procyon + "</U></FONT></HTML>");
      link.setCursor(new Cursor(Cursor.HAND_CURSOR));
      link.addMouseListener(new LinkListener(procyon,link));
      pane.add(link);
      pane.add(new JLabel("Version: " + Procyon.version()));
      pane.add(new JLabel("(c) 2018 Mike Strobel"));
      String rsyntax="https://github.com/bobbylight/RSyntaxTextArea";
      link=new JLabel("<HTML><FONT color=\"#000099\"><U>" + rsyntax + "</U></FONT></HTML>");
      link.setCursor(new Cursor(Cursor.HAND_CURSOR));
      link.addMouseListener(new LinkListener(rsyntax,link));
      pane.add(link);
      pane.add(new JLabel("Version: 3.0.2"));
      pane.add(new JLabel("(c) 2019 Robert Futrell"));
      pane.add(new JLabel(" "));
      JOptionPane.showMessageDialog(null,pane);
    }
  }
);
  helpMenu.add(menuItem);
}
