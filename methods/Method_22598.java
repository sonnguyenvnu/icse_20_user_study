public void rebuildImportMenu(){
  if (importMenu == null) {
    importMenu=new JMenu(Language.text("menu.library"));
  }
 else {
    importMenu.removeAll();
  }
  JMenuItem addLib=new JMenuItem(Language.text("menu.library.add_library"));
  addLib.addActionListener(new ActionListener(){
    public void actionPerformed(    ActionEvent e){
      ContributionManager.openLibraries();
    }
  }
);
  importMenu.add(addLib);
  importMenu.addSeparator();
  rebuildLibraryList();
  ActionListener listener=new ActionListener(){
    public void actionPerformed(    ActionEvent e){
      base.activeEditor.handleImportLibrary(e.getActionCommand());
    }
  }
;
  if (coreLibraries.size() == 0) {
    JMenuItem item=new JMenuItem(getTitle() + " " + Language.text("menu.library.no_core_libraries"));
    item.setEnabled(false);
    importMenu.add(item);
  }
 else {
    for (    Library library : coreLibraries) {
      JMenuItem item=new JMenuItem(library.getName());
      item.addActionListener(listener);
      item.setActionCommand(library.getName());
      importMenu.add(item);
    }
  }
  if (contribLibraries.size() != 0) {
    importMenu.addSeparator();
    JMenuItem contrib=new JMenuItem(Language.text("menu.library.contributed"));
    contrib.setEnabled(false);
    importMenu.add(contrib);
    HashMap<String,JMenu> subfolders=new HashMap<>();
    for (    Library library : contribLibraries) {
      JMenuItem item=new JMenuItem(library.getName());
      item.addActionListener(listener);
      item.setActionCommand(library.getName());
      String group=library.getGroup();
      if (group != null) {
        JMenu subMenu=subfolders.get(group);
        if (subMenu == null) {
          subMenu=new JMenu(group);
          importMenu.add(subMenu);
          subfolders.put(group,subMenu);
        }
        subMenu.add(item);
      }
 else {
        importMenu.add(item);
      }
    }
  }
}
