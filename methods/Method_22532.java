private void makeFrame(){
  frame=new JFrame(title);
  frame.setMinimumSize(Toolkit.zoom(750,500));
  tabs=new ManagerTabs(base);
  makeAndShowTab(false,true);
  tabs.addPanel(librariesTab,"Libraries");
  tabs.addPanel(modesTab,"Modes");
  tabs.addPanel(toolsTab,"Tools");
  tabs.addPanel(examplesTab,"Examples");
  tabs.addPanel(updatesTab,"Updates");
  frame.setResizable(true);
  Container c=frame.getContentPane();
  c.add(tabs);
  c.setBackground(base.getDefaultMode().getColor("manager.tab.background"));
  frame.validate();
  frame.repaint();
  Toolkit.setIcon(frame);
  registerDisposeListeners();
  frame.pack();
  frame.setLocationRelativeTo(null);
}
