protected void buildErrorPanel(){
  errorPanel=new JPanel();
  GroupLayout layout=new GroupLayout(errorPanel);
  layout.setAutoCreateGaps(true);
  layout.setAutoCreateContainerGaps(true);
  errorPanel.setLayout(layout);
  errorMessage=new JTextPane();
  errorMessage.setEditable(false);
  errorMessage.setContentType("text/html");
  errorMessage.setText("<html><body><center>Could not connect to the Processing server.<br>" + "Contributions cannot be installed or updated without an Internet connection.<br>" + "Please verify your network connection again, then try connecting again.</center></body></html>");
  DetailPanel.setTextStyle(errorMessage,"1em");
  Dimension dim=new Dimension(550,60);
  errorMessage.setMaximumSize(dim);
  errorMessage.setMinimumSize(dim);
  errorMessage.setOpaque(false);
  closeButton=Toolkit.createIconButton("manager/close");
  closeButton.setContentAreaFilled(false);
  closeButton.addActionListener(new ActionListener(){
    public void actionPerformed(    ActionEvent e){
      contribDialog.makeAndShowTab(false,false);
    }
  }
);
  tryAgainButton=new JButton("Try Again");
  tryAgainButton.setFont(ManagerFrame.NORMAL_PLAIN);
  tryAgainButton.addActionListener(new ActionListener(){
    public void actionPerformed(    ActionEvent e){
      contribDialog.makeAndShowTab(false,true);
      contribDialog.downloadAndUpdateContributionListing(editor.getBase());
    }
  }
);
  layout.setHorizontalGroup(layout.createSequentialGroup().addPreferredGap(LayoutStyle.ComponentPlacement.RELATED,GroupLayout.PREFERRED_SIZE,Short.MAX_VALUE).addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER).addComponent(errorMessage).addComponent(tryAgainButton,StatusPanel.BUTTON_WIDTH,StatusPanel.BUTTON_WIDTH,StatusPanel.BUTTON_WIDTH)).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED,GroupLayout.PREFERRED_SIZE,Short.MAX_VALUE).addComponent(closeButton));
  layout.setVerticalGroup(layout.createSequentialGroup().addGroup(layout.createParallelGroup().addComponent(errorMessage).addComponent(closeButton)).addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED).addComponent(tryAgainButton));
  errorPanel.setBackground(Color.PINK);
  errorPanel.validate();
}
