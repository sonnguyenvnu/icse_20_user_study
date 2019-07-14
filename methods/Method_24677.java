public JMenu buildHelpMenu(){
  JMenu menu=new JMenu(Language.text("menu.help"));
  JMenuItem item;
  if (!Platform.isMacOS()) {
    item=new JMenuItem(Language.text("menu.help.about"));
    item.addActionListener(new ActionListener(){
      public void actionPerformed(      ActionEvent e){
        new About(JavaEditor.this);
      }
    }
);
    menu.add(item);
  }
  item=new JMenuItem(Language.text("menu.help.welcome"));
  item.addActionListener(new ActionListener(){
    public void actionPerformed(    ActionEvent e){
      try {
        new Welcome(base,Preferences.getSketchbookPath().equals(Preferences.getOldSketchbookPath()));
      }
 catch (      IOException ioe) {
        Messages.showWarning("Unwelcome Error","Please report this error to\n" + "https://github.com/processing/processing/issues",ioe);
      }
    }
  }
);
  menu.add(item);
  item=new JMenuItem(Language.text("menu.help.environment"));
  item.addActionListener(new ActionListener(){
    public void actionPerformed(    ActionEvent e){
      showReference("environment" + File.separator + "index.html");
    }
  }
);
  menu.add(item);
  item=new JMenuItem(Language.text("menu.help.reference"));
  item.addActionListener(new ActionListener(){
    public void actionPerformed(    ActionEvent e){
      showReference("index.html");
    }
  }
);
  menu.add(item);
  item=Toolkit.newJMenuItemShift(Language.text("menu.help.find_in_reference"),'F');
  item.addActionListener(new ActionListener(){
    public void actionPerformed(    ActionEvent e){
      if (textarea.isSelectionActive()) {
        handleFindReference();
      }
 else {
        statusNotice(Language.text("editor.status.find_reference.select_word_first"));
      }
    }
  }
);
  menu.add(item);
  menu.addSeparator();
  final JMenu libRefSubmenu=new JMenu(Language.text("menu.help.libraries_reference"));
  libRefSubmenu.addMenuListener(new MenuListener(){
    @Override public void menuSelected(    MenuEvent e){
      boolean isCoreLibMenuItemAdded=addLibReferencesToSubMenu(mode.coreLibraries,libRefSubmenu);
      if (isCoreLibMenuItemAdded && !mode.contribLibraries.isEmpty()) {
        libRefSubmenu.addSeparator();
      }
      boolean isContribLibMenuItemAdded=addLibReferencesToSubMenu(mode.contribLibraries,libRefSubmenu);
      if (!isContribLibMenuItemAdded && !isCoreLibMenuItemAdded) {
        JMenuItem emptyMenuItem=new JMenuItem(Language.text("menu.help.empty"));
        emptyMenuItem.setEnabled(false);
        emptyMenuItem.setFocusable(false);
        emptyMenuItem.setFocusPainted(false);
        libRefSubmenu.add(emptyMenuItem);
      }
 else       if (!isContribLibMenuItemAdded && !mode.coreLibraries.isEmpty()) {
        libRefSubmenu.removeAll();
        addLibReferencesToSubMenu(mode.coreLibraries,libRefSubmenu);
      }
    }
    @Override public void menuDeselected(    MenuEvent e){
      libRefSubmenu.removeAll();
    }
    @Override public void menuCanceled(    MenuEvent e){
      menuDeselected(e);
    }
  }
);
  menu.add(libRefSubmenu);
  final JMenu toolRefSubmenu=new JMenu(Language.text("menu.help.tools_reference"));
  toolRefSubmenu.addMenuListener(new MenuListener(){
    @Override public void menuSelected(    MenuEvent e){
      boolean isCoreToolMenuItemAdded=false;
      boolean isContribToolMenuItemAdded=false;
      List<ToolContribution> contribTools=base.getToolContribs();
      isCoreToolMenuItemAdded=addToolReferencesToSubMenu(base.getCoreTools(),toolRefSubmenu);
      if (isCoreToolMenuItemAdded && !contribTools.isEmpty())       toolRefSubmenu.addSeparator();
      isContribToolMenuItemAdded=addToolReferencesToSubMenu(contribTools,toolRefSubmenu);
      if (!isContribToolMenuItemAdded && !isCoreToolMenuItemAdded) {
        toolRefSubmenu.removeAll();
        final JMenuItem emptyMenuItem=new JMenuItem(Language.text("menu.help.empty"));
        emptyMenuItem.setEnabled(false);
        emptyMenuItem.setBorderPainted(false);
        emptyMenuItem.setFocusable(false);
        emptyMenuItem.setFocusPainted(false);
        toolRefSubmenu.add(emptyMenuItem);
      }
 else       if (!isContribToolMenuItemAdded && !contribTools.isEmpty()) {
        toolRefSubmenu.removeAll();
        addToolReferencesToSubMenu(base.getCoreTools(),toolRefSubmenu);
      }
    }
    @Override public void menuDeselected(    MenuEvent e){
      toolRefSubmenu.removeAll();
    }
    @Override public void menuCanceled(    MenuEvent e){
      menuDeselected(e);
    }
  }
);
  menu.add(toolRefSubmenu);
  menu.addSeparator();
  item=new JMenuItem(Language.text("menu.help.online"));
  item.setEnabled(false);
  menu.add(item);
  item=new JMenuItem(Language.text("menu.help.getting_started"));
  item.addActionListener(new ActionListener(){
    public void actionPerformed(    ActionEvent e){
      Platform.openURL(Language.text("menu.help.getting_started.url"));
    }
  }
);
  menu.add(item);
  item=new JMenuItem(Language.text("menu.help.troubleshooting"));
  item.addActionListener(new ActionListener(){
    public void actionPerformed(    ActionEvent e){
      Platform.openURL(Language.text("menu.help.troubleshooting.url"));
    }
  }
);
  menu.add(item);
  item=new JMenuItem(Language.text("menu.help.faq"));
  item.addActionListener(new ActionListener(){
    public void actionPerformed(    ActionEvent e){
      Platform.openURL(Language.text("menu.help.faq.url"));
    }
  }
);
  menu.add(item);
  item=new JMenuItem(Language.text("menu.help.foundation"));
  item.addActionListener(new ActionListener(){
    public void actionPerformed(    ActionEvent e){
      Platform.openURL(Language.text("menu.help.foundation.url"));
    }
  }
);
  menu.add(item);
  item=new JMenuItem(Language.text("menu.help.visit"));
  item.addActionListener(new ActionListener(){
    public void actionPerformed(    ActionEvent e){
      Platform.openURL(Language.text("menu.help.visit.url"));
    }
  }
);
  menu.add(item);
  return menu;
}
