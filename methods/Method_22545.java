@Override protected void setLayout(boolean error,boolean loading){
  if (progressBar == null) {
    progressBar=new JProgressBar();
    progressBar.setVisible(false);
    buildErrorPanel();
    loaderLabel=new JLabel(Toolkit.getLibIcon("manager/loader.gif"));
    loaderLabel.setOpaque(false);
  }
  GroupLayout layout=new GroupLayout(this);
  setLayout(layout);
  layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER).addComponent(loaderLabel).addComponent(contributionListPanel).addComponent(errorPanel).addComponent(statusPanel));
  layout.setVerticalGroup(layout.createSequentialGroup().addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER).addComponent(loaderLabel).addComponent(contributionListPanel)).addComponent(errorPanel).addComponent(statusPanel,GroupLayout.PREFERRED_SIZE,GroupLayout.DEFAULT_SIZE,GroupLayout.PREFERRED_SIZE));
  layout.setHonorsVisibility(contributionListPanel,false);
  setBackground(Color.WHITE);
}
