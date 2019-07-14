/** 
 * Gimpy file menu to be used on OS X when no sketches are open.
 */
static protected JMenu buildFileMenu(final Base base){
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
      base.getNextMode().showSketchbookFrame();
    }
  }
);
  fileMenu.add(item);
  item=Toolkit.newJMenuItemShift(Language.text("menu.file.examples"),'O');
  item.addActionListener(new ActionListener(){
    public void actionPerformed(    ActionEvent e){
      base.thinkDifferentExamples();
    }
  }
);
  fileMenu.add(item);
  return fileMenu;
}
