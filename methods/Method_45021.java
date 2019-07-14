private void buildEditMenu(JMenu editMenu){
  editMenu.removeAll();
  JMenuItem menuItem=new JMenuItem("Cut");
  menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X,Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
  menuItem.setEnabled(false);
  editMenu.add(menuItem);
  menuItem=new JMenuItem("Copy");
  menuItem.addActionListener(new DefaultEditorKit.CopyAction());
  menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C,Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
  editMenu.add(menuItem);
  menuItem=new JMenuItem("Paste");
  menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V,Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
  menuItem.setEnabled(false);
  editMenu.add(menuItem);
  editMenu.addSeparator();
  menuItem=new JMenuItem("Select All");
  menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A,Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
  menuItem.addActionListener(new ActionListener(){
    @Override public void actionPerformed(    ActionEvent e){
      mainWindow.onSelectAllMenu();
    }
  }
);
  editMenu.add(menuItem);
  editMenu.addSeparator();
  menuItem=new JMenuItem("Find...");
  menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F,Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
  menuItem.addActionListener(new ActionListener(){
    @Override public void actionPerformed(    ActionEvent e){
      mainWindow.onFindMenu();
    }
  }
);
  editMenu.add(menuItem);
  menuItem=new JMenuItem("Find Next");
  menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F3,0));
  menuItem.addActionListener(new ActionListener(){
    @Override public void actionPerformed(    ActionEvent e){
      if (mainWindow.findBox != null)       mainWindow.findBox.fireExploreAction(true);
    }
  }
);
  editMenu.add(menuItem);
  menuItem=new JMenuItem("Find Previous");
  menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F3,InputEvent.SHIFT_DOWN_MASK));
  menuItem.addActionListener(new ActionListener(){
    @Override public void actionPerformed(    ActionEvent e){
      if (mainWindow.findBox != null)       mainWindow.findBox.fireExploreAction(false);
    }
  }
);
  editMenu.add(menuItem);
  menuItem=new JMenuItem("Find All");
  menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_G,Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
  menuItem.addActionListener(new ActionListener(){
    @Override public void actionPerformed(    ActionEvent e){
      mainWindow.onFindAllMenu();
    }
  }
);
  editMenu.add(menuItem);
}
