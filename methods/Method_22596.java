protected void rebuildToolbarMenu(){
  JMenuItem item;
  if (toolbarMenu == null) {
    toolbarMenu=new JMenu();
  }
 else {
    toolbarMenu.removeAll();
  }
  item=Toolkit.newJMenuItem("Open...",'O');
  item.addActionListener(new ActionListener(){
    public void actionPerformed(    ActionEvent e){
      base.handleOpenPrompt();
    }
  }
);
  toolbarMenu.add(item);
  insertToolbarRecentMenu();
  item=Toolkit.newJMenuItemShift("Examples...",'O');
  item.addActionListener(new ActionListener(){
    public void actionPerformed(    ActionEvent e){
      showExamplesFrame();
    }
  }
);
  toolbarMenu.add(item);
  item=new JMenuItem(Language.text("examples.add_examples"));
  item.addActionListener(new ActionListener(){
    public void actionPerformed(    ActionEvent e){
      ContributionManager.openExamples();
    }
  }
);
  toolbarMenu.add(item);
  toolbarMenu.addSeparator();
  base.populateSketchbookMenu(toolbarMenu);
}
