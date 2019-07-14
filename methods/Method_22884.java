protected JMenu buildFileMenu(JMenuItem[] exportItems){
  JMenuItem item;
  JMenu fileMenu=new JMenu(Language.text("menu.file"));
  item=Toolkit.newJMenuItem(Language.text("menu.file.new"),'N');
  item.addActionListener(new ActionListener(){
    public void actionPerformed(    ActionEvent e){
      base.handleNew();
    }
  }
);
  fileMenu.add(item);
  item=Toolkit.newJMenuItem(Language.text("menu.file.open"),'O');
  item.addActionListener(new ActionListener(){
    public void actionPerformed(    ActionEvent e){
      base.handleOpenPrompt();
    }
  }
);
  fileMenu.add(item);
  item=Toolkit.newJMenuItemShift(Language.text("menu.file.sketchbook"),'K');
  item.addActionListener(new ActionListener(){
    @Override public void actionPerformed(    ActionEvent e){
      mode.showSketchbookFrame();
    }
  }
);
  fileMenu.add(item);
  item=Toolkit.newJMenuItemShift(Language.text("menu.file.examples"),'O');
  item.addActionListener(new ActionListener(){
    public void actionPerformed(    ActionEvent e){
      mode.showExamplesFrame();
    }
  }
);
  fileMenu.add(item);
  item=Toolkit.newJMenuItem(Language.text("menu.file.close"),'W');
  item.addActionListener(new ActionListener(){
    public void actionPerformed(    ActionEvent e){
      base.handleClose(Editor.this,false);
    }
  }
);
  fileMenu.add(item);
  item=Toolkit.newJMenuItem(Language.text("menu.file.save"),'S');
  item.addActionListener(new ActionListener(){
    public void actionPerformed(    ActionEvent e){
      handleSave(false);
    }
  }
);
  fileMenu.add(item);
  item=Toolkit.newJMenuItemShift(Language.text("menu.file.save_as"),'S');
  item.addActionListener(new ActionListener(){
    public void actionPerformed(    ActionEvent e){
      handleSaveAs();
    }
  }
);
  fileMenu.add(item);
  if (exportItems != null) {
    for (    JMenuItem ei : exportItems) {
      fileMenu.add(ei);
    }
  }
  fileMenu.addSeparator();
  item=Toolkit.newJMenuItemShift(Language.text("menu.file.page_setup"),'P');
  item.addActionListener(new ActionListener(){
    public void actionPerformed(    ActionEvent e){
      handlePageSetup();
    }
  }
);
  fileMenu.add(item);
  item=Toolkit.newJMenuItem(Language.text("menu.file.print"),'P');
  item.addActionListener(new ActionListener(){
    public void actionPerformed(    ActionEvent e){
      handlePrint();
    }
  }
);
  fileMenu.add(item);
  if (!Platform.isMacOS()) {
    fileMenu.addSeparator();
    item=Toolkit.newJMenuItem(Language.text("menu.file.preferences"),',');
    item.addActionListener(new ActionListener(){
      public void actionPerformed(      ActionEvent e){
        base.handlePrefs();
      }
    }
);
    fileMenu.add(item);
    fileMenu.addSeparator();
    item=Toolkit.newJMenuItem(Language.text("menu.file.quit"),'Q');
    item.addActionListener(new ActionListener(){
      public void actionPerformed(      ActionEvent e){
        base.handleQuit();
      }
    }
);
    fileMenu.add(item);
  }
  return fileMenu;
}
