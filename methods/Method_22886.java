protected JMenu buildSketchMenu(JMenuItem[] runItems){
  JMenuItem item;
  sketchMenu=new JMenu(Language.text("menu.sketch"));
  for (  JMenuItem mi : runItems) {
    sketchMenu.add(mi);
  }
  sketchMenu.addSeparator();
  sketchMenu.add(mode.getImportMenu());
  item=Toolkit.newJMenuItem(Language.text("menu.sketch.show_sketch_folder"),'K');
  item.addActionListener(new ActionListener(){
    public void actionPerformed(    ActionEvent e){
      Platform.openFolder(sketch.getFolder());
    }
  }
);
  sketchMenu.add(item);
  item.setEnabled(Platform.openFolderAvailable());
  item=new JMenuItem(Language.text("menu.sketch.add_file"));
  item.addActionListener(new ActionListener(){
    public void actionPerformed(    ActionEvent e){
      sketch.handleAddFile();
    }
  }
);
  sketchMenu.add(item);
  if (runItems != null && runItems.length != 0) {
    sketchMenu.addSeparator();
  }
  sketchMenu.addMenuListener(new MenuListener(){
    @Override public void menuSelected(    MenuEvent event){
      JMenuItem item;
      for (      final Editor editor : base.getEditors()) {
        if (getSketch().getMainFilePath().equals(editor.getSketch().getMainFilePath())) {
          item=new JCheckBoxMenuItem(editor.getSketch().getName());
          item.setSelected(true);
        }
 else {
          item=new JMenuItem(editor.getSketch().getName());
        }
        item.setText(editor.getSketch().getName() + " (" + editor.getMode().getTitle() + ")");
        item.addActionListener(new ActionListener(){
          @Override public void actionPerformed(          ActionEvent e){
            editor.setState(Frame.NORMAL);
            editor.setVisible(true);
            editor.toFront();
          }
        }
);
        sketchMenu.add(item);
        menuList.add(item);
        Toolkit.setMenuMnemsInside(sketchMenu);
      }
    }
    @Override public void menuDeselected(    MenuEvent event){
      for (      JMenuItem item : menuList) {
        sketchMenu.remove(item);
      }
      menuList.clear();
    }
    @Override public void menuCanceled(    MenuEvent event){
      menuDeselected(event);
    }
  }
);
  return sketchMenu;
}
