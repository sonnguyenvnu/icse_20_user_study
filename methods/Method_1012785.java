@SuppressWarnings("serial") public JComponent build(){
  ComponentOrientation orientation=ComponentOrientation.getOrientation(PMS.getLocale());
  String colSpec=FormLayoutUtil.getColSpec("pref, pref:grow, pref, 3dlu, pref, pref, pref",orientation);
  int cols=colSpec.split(",").length;
  FormLayout layout=new FormLayout(colSpec,"p, fill:10:grow, p, p");
  PanelBuilder builder=new PanelBuilder(layout);
  builder.opaque(true);
  CellConstraints cc=new CellConstraints();
  JPanel jSearchPanel=new JPanel();
  jSearchPanel.setLayout(new BoxLayout(jSearchPanel,BoxLayout.LINE_AXIS));
  jSearchPanel.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
  JLabel jFilterLabel=new JLabel(Messages.getString("TracesTab.24") + ":");
  jFilterLabel.setDisplayedMnemonic(KeyEvent.VK_F);
  jFilterLabel.setToolTipText(Messages.getString("TracesTab.33"));
  jTracesFilter=new JComboBox<>(levelStrings);
  jTracesFilter.setSelectedIndex(findLevelsIdx(configuration.getLoggingFilterLogsTab()));
  jFilterLabel.setLabelFor(jTracesFilter);
  jTracesFilter.setToolTipText(Messages.getString("TracesTab.33"));
  jTracesFilter.addActionListener(new ActionListener(){
    @Override public void actionPerformed(    ActionEvent e){
      configuration.setLoggingFilterLogsTab(logLevels[jTracesFilter.getSelectedIndex()]);
      LoggingConfig.setTracesFilter();
    }
  }
);
  jSearchPanel.add(jFilterLabel);
  jSearchPanel.add(Box.createRigidArea(new Dimension(5,0)));
  jSearchPanel.add(jTracesFilter);
  jSearchPanel.add(Box.createRigidArea(new Dimension(5,0)));
  jSearchBox=new JTextField();
  jSearchBox.setBackground(new Color(248,248,248));
  jSearchBox.setToolTipText(Messages.getString("TracesTab.34"));
  jSearchPanel.add(jSearchBox);
  jSearchPanel.add(Box.createRigidArea(new Dimension(5,0)));
  JButton jSearchButton=new JButton(Messages.getString("PMS.144"));
  jSearchButton.setMnemonic(KeyEvent.VK_S);
  jSearchButton.setToolTipText(Messages.getString("TracesTab.34"));
  jSearchPanel.add(jSearchButton);
  jSearchPanel.add(jCSSpace=Box.createRigidArea(new Dimension(5,0)));
  jCSSearch=new JCheckBox(Messages.getString("TracesTab.19"),configuration.getGUILogSearchCaseSensitive());
  jCSSearch.setMnemonic(KeyEvent.VK_C);
  jCSSearch.setToolTipText(Messages.getString("TracesTab.35"));
  jSearchPanel.add(jCSSearch);
  jSearchPanel.add(jRESpace=Box.createRigidArea(new Dimension(5,0)));
  jRESearch=new JCheckBox("RegEx",configuration.getGUILogSearchRegEx());
  jRESearch.setMnemonic(KeyEvent.VK_R);
  jRESearch.setToolTipText(Messages.getString("TracesTab.36"));
  jSearchPanel.add(jRESearch);
  jSearchPanel.add(jMLSpace=Box.createRigidArea(new Dimension(5,0)));
  jMLSearch=new JCheckBox(Messages.getString("TracesTab.20"),configuration.getGUILogSearchMultiLine());
  jMLSearch.setMnemonic(KeyEvent.VK_M);
  jMLSearch.setToolTipText(Messages.getString("TracesTab.37"));
  jSearchPanel.add(jMLSearch);
  jSearchPanel.add(jBufferSpace1=Box.createRigidArea(new Dimension(4,0)));
  jSearchPanel.add(jBufferSeparator=new JSeparator(SwingConstants.VERTICAL));
  jSearchPanel.add(jBufferSpace2=Box.createRigidArea(new Dimension(4,0)));
  jBufferLabel=new JLabel(Messages.getString("TracesTab.17"));
  jBufferLabel.setDisplayedMnemonic(KeyEvent.VK_B);
  jBufferLabel.setToolTipText(Messages.getString("TracesTab.38"));
  jLineBuffer=new CustomJSpinner(new SpinnerIntModel(configuration.getLoggingLogsTabLinebuffer(),PmsConfiguration.LOGGING_LOGS_TAB_LINEBUFFER_MIN,PmsConfiguration.LOGGING_LOGS_TAB_LINEBUFFER_MAX,PmsConfiguration.LOGGING_LOGS_TAB_LINEBUFFER_STEP),true);
  jLineBuffer.setToolTipText(Messages.getString("TracesTab.38"));
  jBufferLabel.setLabelFor(jLineBuffer);
  jSearchPanel.add(jBufferLabel);
  jSearchPanel.add(jBufferSpace3=Box.createRigidArea(new Dimension(5,0)));
  jSearchPanel.add(jLineBuffer);
  jLineBuffer.addChangeListener(new ChangeListener(){
    @Override public void stateChanged(    ChangeEvent e){
      jList.setMaxLines((Integer)jLineBuffer.getValue());
      configuration.setLoggingLogsTabLinebuffer(jList.getMaxLines());
    }
  }
);
  jSearchBox.addActionListener(new ActionListener(){
    @Override public void actionPerformed(    ActionEvent e){
      searchTraces();
    }
  }
);
  jSearchButton.addActionListener(new ActionListener(){
    @Override public void actionPerformed(    ActionEvent e){
      searchTraces();
    }
  }
);
  jCSSearch.addActionListener(new ActionListener(){
    @Override public void actionPerformed(    ActionEvent e){
      configuration.setGUILogSearchCaseSensitive(jCSSearch.isSelected());
    }
  }
);
  jRESearch.addActionListener(new ActionListener(){
    @Override public void actionPerformed(    ActionEvent e){
      configuration.setGUILogSearchRegEx(jRESearch.isSelected());
    }
  }
);
  jMLSearch.addActionListener(new ActionListener(){
    @Override public void actionPerformed(    ActionEvent e){
      configuration.setGUILogSearchMultiLine(jMLSearch.isSelected());
    }
  }
);
  builder.add(jSearchPanel,cc.xyw(1,1,cols));
  jList=new TextAreaFIFO(configuration.getLoggingLogsTabLinebuffer(),500);
  jList.setEditable(false);
  jList.setBackground(Color.WHITE);
  jList.setFont(new Font(Font.MONOSPACED,Font.PLAIN,jList.getFont().getSize()));
  final JPopupMenu popup=new JPopupMenu();
  Action copy=jList.getActionMap().get("copy-to-clipboard");
  JMenuItem copyItem=new JMenuItem(copy);
  copyItem.setText(Messages.getString("Generic.Copy"));
  popup.add(copyItem);
  popup.addSeparator();
  JMenuItem clearItem=new JMenuItem(Messages.getString("TracesTab.3"));
  clearItem.addActionListener(new ActionListener(){
    @Override public void actionPerformed(    ActionEvent e){
      jList.setText("");
    }
  }
);
  popup.add(clearItem);
  jList.addMouseListener(new PopupTriggerMouseListener(popup,jList));
  jList.addKeyListener(new KeyListener(){
    @Override public void keyPressed(    KeyEvent e){
      if (e.getKeyCode() == KeyEvent.VK_ENTER) {
        searchTraces();
      }
    }
    @Override public void keyReleased(    KeyEvent e){
    }
    @Override public void keyTyped(    KeyEvent e){
    }
  }
);
  jListPane=new JScrollPane(jList,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
  jListPane.setBorder(BorderFactory.createEmptyBorder());
  new SmartScroller(jListPane);
  builder.add(jListPane,cc.xyw(1,2,cols));
  jOptionsPanel=new JPanel();
  jOptionsPanel.setLayout(new BoxLayout(jOptionsPanel,BoxLayout.LINE_AXIS));
  jOptionsPanel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(5,5,5,5),BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED),Messages.getString("TracesTab.26")),BorderFactory.createEmptyBorder(10,5,10,5))));
  jBuffered=new JCheckBox(Messages.getString("TracesTab.25"),configuration.getLoggingBuffered());
  jBuffered.setMnemonic(KeyEvent.VK_U);
  jBuffered.setToolTipText(Messages.getString("TracesTab.43"));
  jBuffered.setHorizontalTextPosition(SwingConstants.LEADING);
  jBuffered.addActionListener(new ActionListener(){
    @Override public void actionPerformed(    ActionEvent e){
      if (PMS.getTraceMode() == 2 && jBuffered.isSelected()) {
        jBuffered.setSelected(false);
        return;
      }
      configuration.setLoggingBuffered(jBuffered.isSelected());
      LoggingConfig.setBuffered(jBuffered.isSelected());
    }
  }
);
  jOptionsPanel.add(jBuffered);
  jOptionsPanel.add(Box.createRigidArea(new Dimension(4,0)));
  boolean useSyslog=configuration.getLoggingUseSyslog();
  jUseSyslog=new JCheckBox(Messages.getString("TracesTab.27"),useSyslog);
  jUseSyslog.setMnemonic(KeyEvent.VK_Y);
  jUseSyslog.setToolTipText(Messages.getString("TracesTab.44"));
  jUseSyslog.setHorizontalTextPosition(SwingConstants.LEADING);
  jUseSyslog.addActionListener(new ActionListener(){
    @Override public void actionPerformed(    ActionEvent e){
      if (PMS.getTraceMode() == 2 && jUseSyslog.isSelected()) {
        jUseSyslog.setSelected(false);
        return;
      }
 else       if (jSyslogHost.getText().trim().isEmpty()) {
        jSearchOutput.setText(Messages.getString("TracesTab.31"));
        jUseSyslog.setSelected(false);
        return;
      }
      jSearchOutput.setText("");
      boolean useSyslog=jUseSyslog.isSelected();
      configuration.setLoggingUseSyslog(useSyslog);
      LoggingConfig.setSyslog();
      jSyslogHost.setEnabled(!useSyslog);
      jSyslogPort.setEnabled(!useSyslog);
      jSyslogFacility.setEnabled(!useSyslog);
    }
  }
);
  jOptionsPanel.add(jUseSyslog);
  jOptionsPanel.add(Box.createRigidArea(new Dimension(4,0)));
  JLabel jSyslogHostLabel=new JLabel(Messages.getString("TracesTab.28"));
  jSyslogHostLabel.setDisplayedMnemonic(KeyEvent.VK_N);
  jSyslogHostLabel.setToolTipText(Messages.getString("TracesTab.45"));
  jOptionsPanel.add(jSyslogHostLabel);
  jOptionsPanel.add(Box.createRigidArea(new Dimension(4,0)));
  jSyslogHost=new JTextField(configuration.getLoggingSyslogHost(),10);
  jSyslogHostLabel.setLabelFor(jSyslogHost);
  jSyslogHost.setToolTipText(Messages.getString("TracesTab.45"));
  jSyslogHost.setEnabled(!useSyslog);
  jSyslogHost.addKeyListener(new KeyListener(){
    @Override public void keyTyped(    KeyEvent e){
    }
    @Override public void keyReleased(    KeyEvent e){
    }
    @Override public void keyPressed(    KeyEvent e){
      if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
        jSyslogHost.setText(configuration.getLoggingSyslogHost());
      }
 else       if (e.getKeyCode() == KeyEvent.VK_ENTER) {
        FocusManager.getCurrentManager().focusNextComponent();
      }
    }
  }
);
  jSyslogHost.setInputVerifier(new InputVerifier(){
    @Override public boolean verify(    JComponent input){
      String s=((JTextField)input).getText().trim();
      if (!s.isEmpty() && !(s.matches("^(([a-zA-Z0-9]|[a-zA-Z0-9][a-zA-Z0-9\\-]*[a-zA-Z0-9])\\.)*([A-Za-z0-9]|[A-Za-z0-9][A-Za-z0-9\\-]*[A-Za-z0-9])$") || s.matches("(^([0-9a-fA-F]{0,4}:)+[0-9a-fA-F]{1,4}([0-9a-fA-F]{0,4}:)*$)|(^([0-9a-fA-F]{0,4}:)*[0-9a-fA-F]{1,4}([0-9a-fA-F]{0,4}:)+$)|(^::$)"))) {
        jSearchOutput.setText(String.format(Messages.getString("TracesTab.32"),s));
        return false;
      }
      jSearchOutput.setText("");
      configuration.setLoggingSyslogHost(jSyslogHost.getText().trim());
      return true;
    }
  }
);
  jOptionsPanel.add(jSyslogHost);
  jOptionsPanel.add(Box.createRigidArea(new Dimension(4,0)));
  JLabel jSyslogPortLabel=new JLabel(Messages.getString("TracesTab.29"));
  jSyslogPortLabel.setToolTipText(Messages.getString("TracesTab.46"));
  jOptionsPanel.add(jSyslogPortLabel);
  jOptionsPanel.add(Box.createRigidArea(new Dimension(4,0)));
  jSyslogPort=new CustomJSpinner(new SpinnerIntModel(configuration.getLoggingSyslogPort(),1,65535),true);
  jSyslogPort.setToolTipText(Messages.getString("TracesTab.46"));
  jSyslogPort.setEnabled(!useSyslog);
  jSyslogPortLabel.setLabelFor(jSyslogPort);
  jSyslogPort.addChangeListener(new ChangeListener(){
    @Override public void stateChanged(    ChangeEvent e){
      configuration.setLoggingSyslogPort((Integer)jSyslogPort.getValue());
    }
  }
);
  jOptionsPanel.add(jSyslogPort);
  jOptionsPanel.add(Box.createRigidArea(new Dimension(4,0)));
  JLabel jSyslogFacilityLabel=new JLabel(Messages.getString("TracesTab.30"));
  jSyslogFacilityLabel.setDisplayedMnemonic(KeyEvent.VK_A);
  jSyslogFacilityLabel.setToolTipText(Messages.getString("TracesTab.47"));
  jOptionsPanel.add(jSyslogFacilityLabel);
  jOptionsPanel.add(Box.createRigidArea(new Dimension(4,0)));
  jSyslogFacility=new JComboBox<>(syslogFacilities);
  jSyslogFacility.setToolTipText(Messages.getString("TracesTab.47"));
  jSyslogFacility.setEnabled(!useSyslog);
  jSyslogFacility.setSelectedIndex(findSyslogFacilityIdx(configuration.getLoggingSyslogFacility()));
  jSyslogFacilityLabel.setLabelFor(jSyslogFacility);
  jSyslogFacility.addActionListener(new ActionListener(){
    @Override public void actionPerformed(    ActionEvent e){
      configuration.setLoggingSyslogFacility(syslogFacilities[jSyslogFacility.getSelectedIndex()]);
    }
  }
);
  jOptionsPanel.add(jSyslogFacility);
  jOptionsPanel.setFocusTraversalPolicyProvider(true);
  builder.add(jOptionsPanel,cc.xyw(1,3,cols));
  jShowOptions=new JCheckBox(Messages.getString("TracesTab.18"),PMS.getTraceMode() != 2 && configuration.getLoggingUseSyslog());
  jShowOptions.setHorizontalTextPosition(SwingConstants.LEADING);
  jShowOptions.setToolTipText(Messages.getString("TracesTab.41"));
  jShowOptions.setMnemonic(KeyEvent.VK_G);
  jShowOptions.setEnabled(PMS.getTraceMode() != 2);
  jShowOptions.addActionListener(new ActionListener(){
    @Override public void actionPerformed(    ActionEvent e){
      jOptionsPanel.setVisible(jShowOptions.isSelected());
      if (jShowOptions.isSelected()) {
        jBuffered.requestFocusInWindow();
      }
    }
  }
);
  builder.add(jShowOptions,cc.xy(3,4));
  JPanel pLogFileButtons=new JPanel(new FlowLayout(FlowLayout.RIGHT));
  HashMap<String,String> logFiles=LoggingConfig.getLogFilePaths();
  for (  Map.Entry<String,String> logger : logFiles.entrySet()) {
    String loggerNameDisplay=logger.getKey();
    if (logger.getKey().toLowerCase().startsWith("default.log")) {
      loggerNameDisplay=Messages.getString("TracesTab.5");
    }
    CustomJButton b=new CustomJButton(loggerNameDisplay);
    if (!logger.getKey().equals(loggerNameDisplay)) {
      b.setMnemonic(KeyEvent.VK_O);
    }
    b.setToolTipText(logger.getValue());
    b.addActionListener(new ActionListener(){
      @Override public void actionPerformed(      ActionEvent e){
        File logFile=new File(((CustomJButton)e.getSource()).getToolTipText());
        try {
          java.awt.Desktop.getDesktop().open(logFile);
        }
 catch (        IOException|UnsupportedOperationException ioe) {
          LOGGER.error("Failed to open file \"{}\" in default editor: {}",logFile,ioe);
        }
      }
    }
);
    pLogFileButtons.add(b);
  }
  builder.add(pLogFileButtons,cc.xy(cols,4));
  final ch.qos.logback.classic.Logger rootLogger=(ch.qos.logback.classic.Logger)LoggerFactory.getLogger(Logger.ROOT_LOGGER_NAME);
  JLabel rootLevelLabel=new JLabel(Messages.getString("TracesTab.11") + ": ");
  rootLevelLabel.setDisplayedMnemonic(KeyEvent.VK_L);
  rootLevelLabel.setToolTipText(Messages.getString("TracesTab.42"));
  JComboBox<String> rootLevel=new JComboBox<>(levelStrings);
  rootLevelLabel.setLabelFor(rootLevel);
  rootLevel.setSelectedIndex(findLevelsIdx(rootLogger.getLevel()));
  rootLevel.setToolTipText(Messages.getString("TracesTab.42"));
  rootLevel.addActionListener(new ActionListener(){
    @Override public void actionPerformed(    ActionEvent e){
      JComboBox<?> cb=(JComboBox<?>)e.getSource();
      rootLogger.setLevel(logLevels[cb.getSelectedIndex()]);
      Level newLevel=rootLogger.getLevel();
      if (newLevel.toInt() > Level.INFO_INT) {
        rootLogger.setLevel(Level.INFO);
      }
      LOGGER.info("Changed debug level to " + newLevel);
      if (newLevel != rootLogger.getLevel()) {
        rootLogger.setLevel(newLevel);
      }
      configuration.setRootLogLevel(newLevel);
    }
  }
);
  builder.add(rootLevelLabel,cc.xy(5,4));
  builder.add(rootLevel,cc.xy(6,4));
  if (PMS.getTraceMode() == 2) {
    rootLevel.setEnabled(false);
  }
  JPanel pLogPackButtons=new JPanel(new FlowLayout(FlowLayout.LEFT));
  if (PMS.getTraceMode() == 0) {
    CustomJButton rebootTrace=new CustomJButton(Messages.getString("TracesTab.12"));
    rebootTrace.setToolTipText(Messages.getString("TracesTab.39"));
    rebootTrace.setMnemonic(KeyEvent.VK_T);
    rebootTrace.addActionListener(new ActionListener(){
      @Override public void actionPerformed(      ActionEvent e){
        int opt=JOptionPane.showConfirmDialog(null,Messages.getString("TracesTab.13"),Messages.getString("TracesTab.14"),JOptionPane.YES_NO_OPTION);
        if (opt == JOptionPane.YES_OPTION) {
          ProcessUtil.reboot("trace");
        }
      }
    }
);
    pLogPackButtons.add(rebootTrace);
  }
  CustomJButton packDbg=new CustomJButton(Messages.getString("TracesTab.4"));
  packDbg.setMnemonic(KeyEvent.VK_P);
  packDbg.setToolTipText(Messages.getString("TracesTab.40"));
  packDbg.addActionListener(new ActionListener(){
    @Override public void actionPerformed(    ActionEvent e){
      JComponent comp=PMS.get().dbgPack().config();
      String[] cancelStr={Messages.getString("Dialog.Close")};
      JOptionPane.showOptionDialog(looksFrame,comp,Messages.getString("Dialog.Options"),JOptionPane.CLOSED_OPTION,JOptionPane.PLAIN_MESSAGE,null,cancelStr,null);
    }
  }
);
  pLogPackButtons.add(packDbg);
  builder.add(pLogPackButtons,cc.xy(1,4));
  builder.add(jSearchOutput,cc.xy(2,4));
  JPanel builtPanel=builder.getPanel();
  builtPanel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_F,InputEvent.CTRL_DOWN_MASK),"Ctrl_F");
  builtPanel.getActionMap().put("Ctrl_F",new AbstractAction(){
    @Override public void actionPerformed(    ActionEvent e){
      jSearchBox.requestFocusInWindow();
    }
  }
);
  builtPanel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_F3,0),"F3");
  builtPanel.getActionMap().put("F3",new AbstractAction(){
    @Override public void actionPerformed(    ActionEvent e){
      searchTraces();
    }
  }
);
  applyViewLevel();
  return builtPanel;
}
