/** 
 * Create the widgets for the header panel which is visible when the library panel is not clicked.
 */
private void addPaneComponents(){
  setLayout(new BorderLayout());
  descriptionPane=new JTextPane();
  descriptionPane.setInheritsPopupMenu(true);
  descriptionPane.setEditable(false);
  Insets margin=descriptionPane.getMargin();
  margin.bottom=0;
  descriptionPane.setMargin(margin);
  descriptionPane.setContentType("text/html");
  setTextStyle(descriptionPane,"0.95em");
  descriptionPane.setOpaque(false);
  if (UIManager.getLookAndFeel().getID().equals("Nimbus")) {
    descriptionPane.setBackground(new Color(0,0,0,0));
  }
  descriptionPane.setBorder(new EmptyBorder(4,7,7,7));
  descriptionPane.setHighlighter(null);
  descriptionPane.addHyperlinkListener(new HyperlinkListener(){
    public void hyperlinkUpdate(    HyperlinkEvent e){
      if (e.getEventType() == HyperlinkEvent.EventType.ACTIVATED) {
        if (isSelected()) {
          if (enableHyperlinks && e.getURL() != null) {
            Platform.openURL(e.getURL().toString());
          }
        }
      }
    }
  }
);
  add(descriptionPane,BorderLayout.CENTER);
  JPanel updateBox=new JPanel();
  updateBox.setLayout(new BorderLayout());
  notificationLabel=new JLabel();
  notificationLabel.setInheritsPopupMenu(true);
  notificationLabel.setVisible(false);
  notificationLabel.setOpaque(false);
  notificationLabel.setFont(ManagerFrame.SMALL_PLAIN);
{
    updateButton=new JButton("Update");
    updateButton.setInheritsPopupMenu(true);
    Dimension dim=new Dimension(BUTTON_WIDTH,updateButton.getPreferredSize().height);
    updateButton.setMinimumSize(dim);
    updateButton.setPreferredSize(dim);
    updateButton.setOpaque(false);
    updateButton.setVisible(false);
    updateButton.addActionListener(new ActionListener(){
      public void actionPerformed(      ActionEvent e){
        update();
      }
    }
);
  }
  updateBox.add(updateButton,BorderLayout.EAST);
  updateBox.add(notificationLabel,BorderLayout.WEST);
  updateBox.setBorder(new EmptyBorder(4,7,7,7));
  updateBox.setOpaque(false);
  add(updateBox,BorderLayout.SOUTH);
  JPanel rightPane=new JPanel();
  rightPane.setInheritsPopupMenu(true);
  rightPane.setOpaque(false);
  rightPane.setLayout(new BoxLayout(rightPane,BoxLayout.Y_AXIS));
  rightPane.setMinimumSize(new Dimension(BUTTON_WIDTH,1));
  add(rightPane,BorderLayout.EAST);
  barButtonCardLayout=new CardLayout();
  barButtonCardPane.setLayout(barButtonCardLayout);
  barButtonCardPane.setInheritsPopupMenu(true);
  barButtonCardPane.setOpaque(false);
  barButtonCardPane.setMinimumSize(new Dimension(BUTTON_WIDTH,1));
{
    installProgressBar=new JProgressBar();
    installProgressBar.setInheritsPopupMenu(true);
    installProgressBar.setStringPainted(true);
    resetInstallProgressBarState();
    Dimension dim=new Dimension(BUTTON_WIDTH,installProgressBar.getPreferredSize().height);
    installProgressBar.setPreferredSize(dim);
    installProgressBar.setMaximumSize(dim);
    installProgressBar.setMinimumSize(dim);
    installProgressBar.setOpaque(false);
    installProgressBar.setAlignmentX(CENTER_ALIGNMENT);
  }
  installRemoveButton=new JButton(" ");
  installRemoveButton.setInheritsPopupMenu(true);
  installRemoveButton.addActionListener(new ActionListener(){
    public void actionPerformed(    ActionEvent e){
      String mode=installRemoveButton.getText();
      if (mode.equals(installText)) {
        install();
      }
 else       if (mode.equals(removeText)) {
        remove();
      }
 else       if (mode.equals(undoText)) {
        undo();
      }
    }
  }
);
  Dimension installButtonDimensions=installRemoveButton.getPreferredSize();
  installButtonDimensions.width=BUTTON_WIDTH;
  installRemoveButton.setPreferredSize(installButtonDimensions);
  installRemoveButton.setMaximumSize(installButtonDimensions);
  installRemoveButton.setMinimumSize(installButtonDimensions);
  installRemoveButton.setOpaque(false);
  installRemoveButton.setAlignmentX(CENTER_ALIGNMENT);
  JPanel barPane=new JPanel();
  barPane.setOpaque(false);
  JPanel buttonPane=new JPanel();
  buttonPane.setOpaque(false);
  buttonPane.add(installRemoveButton);
  barButtonCardPane.add(buttonPane,BUTTON_CONSTRAINT);
  barButtonCardPane.add(barPane,PROGRESS_BAR_CONSTRAINT);
  barButtonCardLayout.show(barButtonCardPane,BUTTON_CONSTRAINT);
  rightPane.add(barButtonCardPane);
  Dimension dim=new Dimension(BUTTON_WIDTH,installRemoveButton.getPreferredSize().height);
  rightPane.setMinimumSize(dim);
  rightPane.setPreferredSize(dim);
}
