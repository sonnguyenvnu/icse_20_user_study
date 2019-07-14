public void populateToolsMenu(JMenu toolsMenu){
  if (internalTools == null) {
    rebuildToolList();
  }
  toolsMenu.removeAll();
  for (  Tool tool : internalTools) {
    toolsMenu.add(createToolItem(tool));
  }
  toolsMenu.addSeparator();
  if (coreTools.size() > 0) {
    for (    Tool tool : coreTools) {
      toolsMenu.add(createToolItem(tool));
    }
    toolsMenu.addSeparator();
  }
  if (contribTools.size() > 0) {
    for (    Tool tool : contribTools) {
      toolsMenu.add(createToolItem(tool));
    }
    toolsMenu.addSeparator();
  }
  JMenuItem item=new JMenuItem(Language.text("menu.tools.add_tool"));
  item.addActionListener(new ActionListener(){
    public void actionPerformed(    ActionEvent e){
      ContributionManager.openTools();
    }
  }
);
  toolsMenu.add(item);
}
