public void addPage(String title,Icon icon,String tip,T page){
  JLabel tabCloseButton=new JLabel(CLOSE_ICON);
  tabCloseButton.setToolTipText("Close this panel");
  tabCloseButton.addMouseListener(new MouseListener(){
    @Override public void mousePressed(    MouseEvent e){
    }
    @Override public void mouseReleased(    MouseEvent e){
    }
    @Override public void mouseEntered(    MouseEvent e){
      ((JLabel)e.getSource()).setIcon(CLOSE_ACTIVE_ICON);
    }
    @Override public void mouseExited(    MouseEvent e){
      ((JLabel)e.getSource()).setIcon(CLOSE_ICON);
    }
    @Override public void mouseClicked(    MouseEvent e){
      removeComponent(page);
    }
  }
);
  JPanel tab=new JPanel(new BorderLayout());
  tab.setBorder(BorderFactory.createEmptyBorder(2,0,3,0));
  tab.setOpaque(false);
  tab.setToolTipText(tip);
  tab.add(new JLabel(title,icon,JLabel.LEADING),BorderLayout.CENTER);
  tab.add(tabCloseButton,BorderLayout.EAST);
  ToolTipManager.sharedInstance().unregisterComponent(tab);
  int index=tabbedPane.getTabCount();
  tabbedPane.addTab(title,page);
  tabbedPane.setTabComponentAt(index,tab);
  setSelectedIndex(index);
  cardLayout.show(this,"tabs");
}
