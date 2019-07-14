private void buildFileMenu(final JMenu fileMenu){
  fileMenu.removeAll();
  JMenuItem menuItem=new JMenuItem("Open File...");
  menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O,Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
  menuItem.addActionListener(new ActionListener(){
    @Override public void actionPerformed(    ActionEvent e){
      mainWindow.onOpenFileMenu();
    }
  }
);
  fileMenu.add(menuItem);
  fileMenu.addSeparator();
  menuItem=new JMenuItem("Close File");
  menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_W,Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
  menuItem.addActionListener(new ActionListener(){
    @Override public void actionPerformed(    ActionEvent e){
      JTabbedPane house=mainWindow.getModel().house;
      if (e.getModifiers() != 2 || house.getTabCount() == 0)       mainWindow.onCloseFileMenu();
 else {
        mainWindow.getModel().closeOpenTab(house.getSelectedIndex());
      }
    }
  }
);
  fileMenu.add(menuItem);
  fileMenu.addSeparator();
  menuItem=new JMenuItem("Save As...");
  menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E,Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
  menuItem.addActionListener(new ActionListener(){
    @Override public void actionPerformed(    ActionEvent e){
      mainWindow.onSaveAsMenu();
    }
  }
);
  fileMenu.add(menuItem);
  menuItem=new JMenuItem("Save All...");
  menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E,Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
  menuItem.addActionListener(new ActionListener(){
    @Override public void actionPerformed(    ActionEvent e){
      mainWindow.onSaveAllMenu();
    }
  }
);
  fileMenu.add(menuItem);
  fileMenu.addSeparator();
  recentFiles=new JMenu("Recent Files");
  fileMenu.add(recentFiles);
  clearRecentFiles=new JMenuItem("Clear Recent Files");
  clearRecentFiles.addActionListener(new ActionListener(){
    @Override public void actionPerformed(    ActionEvent e){
      RecentFiles.paths.clear();
      RecentFiles.save();
      updateRecentFiles();
    }
  }
);
  fileMenu.add(clearRecentFiles);
  fileMenu.addSeparator();
  if (!Boolean.getBoolean("apple.laf.useScreenMenuBar")) {
    menuItem=new JMenuItem("Exit");
    menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F4,ActionEvent.ALT_MASK));
    menuItem.addActionListener(new ActionListener(){
      @Override public void actionPerformed(      ActionEvent e){
        mainWindow.onExitMenu();
      }
    }
);
    fileMenu.add(menuItem);
  }
}
