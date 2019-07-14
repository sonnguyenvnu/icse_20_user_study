protected void setLayout(boolean activateErrorPanel,boolean isLoading){
  if (progressBar == null) {
    progressBar=new JProgressBar();
    progressBar.setVisible(false);
    createComponents();
    buildErrorPanel();
    loaderLabel=new JLabel(Toolkit.getLibIcon("manager/loader.gif"));
    loaderLabel.setOpaque(false);
    loaderLabel.setBackground(Color.WHITE);
  }
  int scrollBarWidth=contributionListPanel.scrollPane.getVerticalScrollBar().getPreferredSize().width;
  GroupLayout layout=new GroupLayout(this);
  setLayout(layout);
  layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER).addGroup(layout.createSequentialGroup().addGap(ManagerFrame.STATUS_WIDTH).addComponent(filterField,FILTER_WIDTH,FILTER_WIDTH,FILTER_WIDTH).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED,GroupLayout.PREFERRED_SIZE,Short.MAX_VALUE).addComponent(categoryChooser,ManagerFrame.AUTHOR_WIDTH,ManagerFrame.AUTHOR_WIDTH,ManagerFrame.AUTHOR_WIDTH).addGap(scrollBarWidth)).addComponent(loaderLabel).addComponent(contributionListPanel).addComponent(errorPanel).addComponent(statusPanel));
  layout.setVerticalGroup(layout.createSequentialGroup().addContainerGap().addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER).addComponent(categoryChooser).addComponent(filterField)).addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED).addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER).addComponent(loaderLabel).addComponent(contributionListPanel)).addComponent(errorPanel).addComponent(statusPanel,GroupLayout.PREFERRED_SIZE,GroupLayout.DEFAULT_SIZE,GroupLayout.PREFERRED_SIZE));
  layout.linkSize(SwingConstants.VERTICAL,categoryChooser,filterField);
  layout.setHonorsVisibility(contributionListPanel,false);
  layout.setHonorsVisibility(categoryChooser,false);
  setBackground(Color.WHITE);
  setBorder(null);
}
