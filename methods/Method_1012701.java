@Override public JComponent config(){
  FormLayout layout=new FormLayout("left:pref, 0:grow","p, 3dlu, p, 3dlu, p, 3dlu, p, 3dlu, p, 12dlu, p, 3dlu, 0:grow");
  PanelBuilder builder=new PanelBuilder(layout);
  builder.border(Borders.EMPTY);
  builder.opaque(false);
  CellConstraints cc=new CellConstraints();
  JComponent cmp=builder.addSeparator(Messages.getString("NetworkTab.5"),cc.xyw(2,1,1));
  cmp=(JComponent)cmp.getComponent(0);
  cmp.setFont(cmp.getFont().deriveFont(Font.BOLD));
  multithreading=new JCheckBox(Messages.getString("MEncoderVideo.35"),configuration.getAvisynthMultiThreading());
  multithreading.setContentAreaFilled(false);
  multithreading.addItemListener(new ItemListener(){
    @Override public void itemStateChanged(    ItemEvent e){
      configuration.setAvisynthMultiThreading((e.getStateChange() == ItemEvent.SELECTED));
    }
  }
);
  builder.add(GuiUtil.getPreferredSizeComponent(multithreading),cc.xy(2,3));
  interframe=new JCheckBox(Messages.getString("AviSynthMEncoder.13"),configuration.getAvisynthInterFrame());
  interframe.setContentAreaFilled(false);
  interframe.addActionListener(new ActionListener(){
    @Override public void actionPerformed(    ActionEvent e){
      configuration.setAvisynthInterFrame(interframe.isSelected());
      if (configuration.getAvisynthInterFrame()) {
        JOptionPane.showMessageDialog(SwingUtilities.getWindowAncestor((Component)PMS.get().getFrame()),Messages.getString("AviSynthMEncoder.16"),Messages.getString("Dialog.Information"),JOptionPane.INFORMATION_MESSAGE);
      }
    }
  }
);
  builder.add(GuiUtil.getPreferredSizeComponent(interframe),cc.xy(2,5));
  interframegpu=new JCheckBox(Messages.getString("AviSynthMEncoder.15"),configuration.getAvisynthInterFrameGPU());
  interframegpu.setContentAreaFilled(false);
  interframegpu.addItemListener(new ItemListener(){
    @Override public void itemStateChanged(    ItemEvent e){
      configuration.setAvisynthInterFrameGPU((e.getStateChange() == ItemEvent.SELECTED));
    }
  }
);
  builder.add(GuiUtil.getPreferredSizeComponent(interframegpu),cc.xy(2,7));
  convertfps=new JCheckBox(Messages.getString("AviSynthMEncoder.3"),configuration.getAvisynthConvertFps());
  convertfps.setContentAreaFilled(false);
  convertfps.addItemListener(new ItemListener(){
    @Override public void itemStateChanged(    ItemEvent e){
      configuration.setAvisynthConvertFps((e.getStateChange() == ItemEvent.SELECTED));
    }
  }
);
  builder.add(GuiUtil.getPreferredSizeComponent(convertfps),cc.xy(2,9));
  String aviSynthScriptInstructions=Messages.getString("AviSynthMEncoder.4") + Messages.getString("AviSynthMEncoder.5") + Messages.getString("AviSynthMEncoder.6") + Messages.getString("AviSynthMEncoder.7") + Messages.getString("AviSynthMEncoder.8");
  JTextArea aviSynthScriptInstructionsContainer=new JTextArea(aviSynthScriptInstructions);
  aviSynthScriptInstructionsContainer.setEditable(false);
  aviSynthScriptInstructionsContainer.setBorder(BorderFactory.createEtchedBorder());
  aviSynthScriptInstructionsContainer.setBackground(new Color(255,255,192));
  aviSynthScriptInstructionsContainer.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(new Color(130,135,144)),BorderFactory.createEmptyBorder(3,5,3,5)));
  builder.add(aviSynthScriptInstructionsContainer,cc.xy(2,11));
  String clip=configuration.getAvisynthScript();
  if (clip == null) {
    clip="";
  }
  StringBuilder sb=new StringBuilder();
  StringTokenizer st=new StringTokenizer(clip,PMS.AVS_SEPARATOR);
  int i=0;
  while (st.hasMoreTokens()) {
    if (i > 0) {
      sb.append("\n");
    }
    sb.append(st.nextToken());
    i++;
  }
  textArea=new JTextArea(sb.toString());
  textArea.addKeyListener(new KeyAdapter(){
    @Override public void keyReleased(    KeyEvent e){
      StringBuilder sb=new StringBuilder();
      StringTokenizer st=new StringTokenizer(textArea.getText(),"\n");
      int i=0;
      while (st.hasMoreTokens()) {
        if (i > 0) {
          sb.append(PMS.AVS_SEPARATOR);
        }
        sb.append(st.nextToken());
        i++;
      }
      configuration.setAvisynthScript(sb.toString());
    }
  }
);
  JScrollPane pane=new JScrollPane(textArea,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
  pane.setPreferredSize(new Dimension(500,350));
  builder.add(pane,cc.xy(2,13));
  configuration.addConfigurationListener(new ConfigurationListener(){
    @Override public void configurationChanged(    ConfigurationEvent event){
      if (event.getPropertyName() == null) {
        return;
      }
      if ((!event.isBeforeUpdate()) && event.getPropertyName().equals(PmsConfiguration.KEY_GPU_ACCELERATION)) {
        interframegpu.setEnabled(configuration.isGPUAcceleration());
      }
    }
  }
);
  return builder.getPanel();
}
