protected boolean exportApplicationPrompt() throws IOException, SketchException {
  JPanel panel=new JPanel();
  panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
  panel.add(Box.createVerticalStrut(6));
  String line1=Language.text("export.description.line1");
  String line2=Language.text("export.description.line2");
  JLabel label1=new JLabel(line1,SwingConstants.CENTER);
  JLabel label2=new JLabel(line2,SwingConstants.CENTER);
  label1.setAlignmentX(Component.LEFT_ALIGNMENT);
  label2.setAlignmentX(Component.LEFT_ALIGNMENT);
  panel.add(label1);
  panel.add(label2);
  panel.add(Box.createVerticalStrut(12));
  windowsButton.setSelected(Preferences.getBoolean(EXPORT_WINDOWS));
  windowsButton.addItemListener(new ItemListener(){
    public void itemStateChanged(    ItemEvent e){
      Preferences.setBoolean(EXPORT_WINDOWS,windowsButton.isSelected());
      updateExportButton();
    }
  }
);
  if (!Platform.isMacOS()) {
    Preferences.setBoolean(EXPORT_MACOSX,false);
  }
  macosxButton.setSelected(Preferences.getBoolean(EXPORT_MACOSX));
  macosxButton.addItemListener(new ItemListener(){
    public void itemStateChanged(    ItemEvent e){
      Preferences.setBoolean(EXPORT_MACOSX,macosxButton.isSelected());
      updateExportButton();
    }
  }
);
  if (!Platform.isMacOS()) {
    macosxButton.setEnabled(false);
    macosxButton.setToolTipText(Language.text("export.tooltip.macosx"));
  }
  linuxButton.setSelected(Preferences.getBoolean(EXPORT_LINUX));
  linuxButton.addItemListener(new ItemListener(){
    public void itemStateChanged(    ItemEvent e){
      Preferences.setBoolean(EXPORT_LINUX,linuxButton.isSelected());
      updateExportButton();
    }
  }
);
  updateExportButton();
  JPanel platformPanel=new JPanel();
  platformPanel.add(windowsButton);
  platformPanel.add(Box.createHorizontalStrut(6));
  platformPanel.add(macosxButton);
  platformPanel.add(Box.createHorizontalStrut(6));
  platformPanel.add(linuxButton);
  platformPanel.setBorder(new TitledBorder(Language.text("export.platforms")));
  platformPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
  panel.add(platformPanel);
  int divWidth=platformPanel.getPreferredSize().width;
  int indent=0;
  final JCheckBox showStopButton=new JCheckBox(Language.text("export.options.show_stop_button"));
  showStopButton.setSelected(Preferences.getBoolean("export.application.stop"));
  showStopButton.addItemListener(new ItemListener(){
    public void itemStateChanged(    ItemEvent e){
      Preferences.setBoolean("export.application.stop",showStopButton.isSelected());
    }
  }
);
  showStopButton.setEnabled(Preferences.getBoolean("export.application.present"));
  showStopButton.setBorder(new EmptyBorder(3,13 + indent,6,13));
  final JCheckBox presentButton=new JCheckBox(Language.text("export.options.present"));
  presentButton.setSelected(Preferences.getBoolean("export.application.present"));
  presentButton.addItemListener(new ItemListener(){
    public void itemStateChanged(    ItemEvent e){
      boolean sal=presentButton.isSelected();
      Preferences.setBoolean("export.application.present",sal);
      showStopButton.setEnabled(sal);
    }
  }
);
  presentButton.setBorder(new EmptyBorder(3,13,3,13));
  JPanel presentPanel=new JPanel();
  presentPanel.setLayout(new BoxLayout(presentPanel,BoxLayout.Y_AXIS));
  Box fullScreenBox=Box.createHorizontalBox();
  fullScreenBox.add(presentButton);
  fullScreenBox.add(new ColorPreference("run.present.bgcolor"));
  fullScreenBox.add(Box.createHorizontalStrut(10));
  fullScreenBox.add(Box.createHorizontalGlue());
  presentPanel.add(fullScreenBox);
  Box showStopBox=Box.createHorizontalBox();
  showStopBox.add(showStopButton);
  showStopBox.add(new ColorPreference("run.present.stop.color"));
  showStopBox.add(Box.createHorizontalStrut(10));
  showStopBox.add(Box.createHorizontalGlue());
  presentPanel.add(showStopBox);
  presentPanel.setBorder(new TitledBorder(Language.text("export.full_screen")));
  presentPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
  panel.add(presentPanel);
  JPanel embedPanel=new JPanel();
  embedPanel.setLayout(new BoxLayout(embedPanel,BoxLayout.Y_AXIS));
  String platformName=null;
  if (Platform.isMacOS()) {
    platformName="Mac OS X";
  }
 else   if (Platform.isWindows()) {
    platformName="Windows (" + Platform.getNativeBits() + "-bit)";
  }
 else   if (Platform.isLinux()) {
    platformName="Linux (" + Platform.getNativeBits() + "-bit)";
  }
  boolean embed=Preferences.getBoolean("export.application.embed_java");
  final String embedWarning="<html><div width=\"" + divWidth + "\"><font size=\"2\">" + "Embedding Java will make the " + platformName + " application " + "larger, but it will be far more likely to work. " + "Users on other platforms will need to " + "<a href=\"\">install Java " + PApplet.javaPlatform + "</a>.";
  final String nopeWarning="<html><div width=\"" + divWidth + "\"><font size=\"2\">" + "Users on all platforms will have to install the latest " + "version of Java " + PApplet.javaPlatform + " from <a href=\"\">http://java.com/download</a>. " + "<br/>&nbsp;";
  final JLabel warningLabel=new JLabel(embed ? embedWarning : nopeWarning);
  warningLabel.addMouseListener(new MouseAdapter(){
    public void mousePressed(    MouseEvent event){
      Platform.openURL("http://java.com/download");
    }
  }
);
  warningLabel.setBorder(new EmptyBorder(3,13 + indent,3,13));
  final JCheckBox embedJavaButton=new JCheckBox(Language.text("export.embed_java.for") + " " + platformName);
  embedJavaButton.setSelected(embed);
  embedJavaButton.addItemListener(new ItemListener(){
    public void itemStateChanged(    ItemEvent e){
      boolean selected=embedJavaButton.isSelected();
      Preferences.setBoolean("export.application.embed_java",selected);
      if (selected) {
        warningLabel.setText(embedWarning);
      }
 else {
        warningLabel.setText(nopeWarning);
      }
    }
  }
);
  embedJavaButton.setBorder(new EmptyBorder(3,13,3,13));
  embedPanel.add(embedJavaButton);
  embedPanel.add(warningLabel);
  embedPanel.setBorder(new TitledBorder(Language.text("export.embed_java")));
  panel.add(embedPanel);
  if (Platform.isMacOS()) {
    JPanel signPanel=new JPanel();
    signPanel.setLayout(new BoxLayout(signPanel,BoxLayout.Y_AXIS));
    signPanel.setBorder(new TitledBorder(Language.text("export.code_signing")));
    String thePain="In recent versions of OS X, Apple has introduced the \u201CGatekeeper\u201D system, " + "which makes it more difficult to run applications like those exported from Processing. ";
    if (new File("/usr/bin/codesign_allocate").exists()) {
      thePain+="This application will be \u201Cself-signed\u201D which means that Finder may report that the " + "application is from an \u201Cunidentified developer\u201D. If the application will not " + "run, try right-clicking the app and selecting Open from the pop-up menu. Or you can visit " + "System Preferences \u2192 Security & Privacy and select Allow apps downloaded from: anywhere. ";
    }
 else {
      thePain+="Gatekeeper requires applications to be \u201Csigned\u201D, or they will be reported as damaged. " + "To prevent this message, install Xcode (and the Command Line Tools) from the App Store, or visit " + "System Preferences \u2192 Security & Privacy and select Allow apps downloaded from: anywhere. ";
    }
    thePain+="To avoid the messages entirely, manually code sign your app. " + "For more information: <a href=\"\">https://developer.apple.com/developer-id/</a>";
    JLabel area=new JLabel("<html><div width=\"" + divWidth + "\"><font size=\"2\">" + thePain + "</div></html>");
    area.setBorder(new EmptyBorder(3,13,3,13));
    signPanel.add(area);
    signPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
    area.addMouseListener(new MouseAdapter(){
      public void mousePressed(      MouseEvent event){
        Platform.openURL("https://developer.apple.com/developer-id/");
      }
    }
);
    panel.add(signPanel);
  }
  final JButton[] options={exportButton,cancelButton};
  final JOptionPane optionPane=new JOptionPane(panel,JOptionPane.PLAIN_MESSAGE,JOptionPane.YES_NO_OPTION,null,options,exportButton);
  final JDialog dialog=new JDialog(this,Language.text("export"),true);
  dialog.setContentPane(optionPane);
  exportButton.addActionListener(new ActionListener(){
    public void actionPerformed(    ActionEvent e){
      optionPane.setValue(exportButton);
    }
  }
);
  cancelButton.addActionListener(new ActionListener(){
    public void actionPerformed(    ActionEvent e){
      optionPane.setValue(cancelButton);
    }
  }
);
  optionPane.addPropertyChangeListener(new PropertyChangeListener(){
    public void propertyChange(    PropertyChangeEvent e){
      String prop=e.getPropertyName();
      if (dialog.isVisible() && (e.getSource() == optionPane) && (prop.equals(JOptionPane.VALUE_PROPERTY))) {
        dialog.setVisible(false);
      }
    }
  }
);
  dialog.pack();
  dialog.setResizable(false);
  Rectangle bounds=getBounds();
  dialog.setLocation(bounds.x + (bounds.width - dialog.getSize().width) / 2,bounds.y + (bounds.height - dialog.getSize().height) / 2);
  dialog.setVisible(true);
  Object value=optionPane.getValue();
  if (value.equals(exportButton)) {
    return jmode.handleExportApplication(sketch);
  }
 else   if (value.equals(cancelButton) || value.equals(-1)) {
    statusNotice(Language.text("export.notice.exporting.cancel"));
  }
  return false;
}
