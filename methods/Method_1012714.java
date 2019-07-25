@Override public JComponent config(){
  ComponentOrientation orientation=ComponentOrientation.getOrientation(PMS.getLocale());
  String colSpec=FormLayoutUtil.getColSpec(COL_SPEC,orientation);
  FormLayout layout=new FormLayout(colSpec,ROW_SPEC);
  PanelBuilder builder=new PanelBuilder(layout);
  builder.border(Borders.EMPTY);
  builder.opaque(false);
  CellConstraints cc=new CellConstraints();
  JComponent cmp=builder.addSeparator(Messages.getString("NetworkTab.5"),FormLayoutUtil.flip(cc.xyw(1,1,15),colSpec,orientation));
  cmp=(JComponent)cmp.getComponent(0);
  cmp.setFont(cmp.getFont().deriveFont(Font.BOLD));
  mencodermt=new JCheckBox(Messages.getString("MEncoderVideo.35"),configuration.getMencoderMT());
  mencodermt.setContentAreaFilled(false);
  mencodermt.addActionListener(new ActionListener(){
    @Override public void actionPerformed(    ActionEvent e){
      configuration.setMencoderMT(mencodermt.isSelected());
    }
  }
);
  mencodermt.setEnabled(Platform.isWindows() || Platform.isMac());
  builder.add(GuiUtil.getPreferredSizeComponent(mencodermt),FormLayoutUtil.flip(cc.xy(1,3),colSpec,orientation));
  skipLoopFilter=new JCheckBox(Messages.getString("MEncoderVideo.0"),configuration.getSkipLoopFilterEnabled());
  skipLoopFilter.setContentAreaFilled(false);
  skipLoopFilter.setToolTipText(Messages.getString("MEncoderVideo.136"));
  skipLoopFilter.addItemListener(new ItemListener(){
    @Override public void itemStateChanged(    ItemEvent e){
      configuration.setSkipLoopFilterEnabled((e.getStateChange() == ItemEvent.SELECTED));
    }
  }
);
  builder.add(GuiUtil.getPreferredSizeComponent(skipLoopFilter),FormLayoutUtil.flip(cc.xyw(3,3,12),colSpec,orientation));
  noskip=new JCheckBox(Messages.getString("MEncoderVideo.2"),configuration.isMencoderNoOutOfSync());
  noskip.setContentAreaFilled(false);
  noskip.addItemListener(new ItemListener(){
    @Override public void itemStateChanged(    ItemEvent e){
      configuration.setMencoderNoOutOfSync((e.getStateChange() == ItemEvent.SELECTED));
    }
  }
);
  builder.add(GuiUtil.getPreferredSizeComponent(noskip),FormLayoutUtil.flip(cc.xy(1,5),colSpec,orientation));
  CustomJButton button=new CustomJButton(Messages.getString("MEncoderVideo.29"));
  button.addActionListener(new ActionListener(){
    @Override public void actionPerformed(    ActionEvent e){
      JPanel codecPanel=new JPanel(new BorderLayout());
      final JTextArea textArea=new JTextArea();
      textArea.setText(configuration.getMencoderCodecSpecificConfig());
      textArea.setFont(new Font("Courier",Font.PLAIN,12));
      JScrollPane scrollPane=new JScrollPane(textArea);
      scrollPane.setPreferredSize(new Dimension(900,100));
      final JTextArea textAreaDefault=new JTextArea();
      textAreaDefault.setText(DEFAULT_CODEC_CONF_SCRIPT);
      textAreaDefault.setBackground(Color.WHITE);
      textAreaDefault.setFont(new Font("Courier",Font.PLAIN,12));
      textAreaDefault.setEditable(false);
      textAreaDefault.setEnabled(configuration.isMencoderIntelligentSync());
      JScrollPane scrollPaneDefault=new JScrollPane(textAreaDefault);
      scrollPaneDefault.setPreferredSize(new Dimension(900,450));
      JPanel customPanel=new JPanel(new BorderLayout());
      intelligentsync=new JCheckBox(Messages.getString("MEncoderVideo.3"),configuration.isMencoderIntelligentSync());
      intelligentsync.setContentAreaFilled(false);
      intelligentsync.addItemListener(new ItemListener(){
        @Override public void itemStateChanged(        ItemEvent e){
          configuration.setMencoderIntelligentSync((e.getStateChange() == ItemEvent.SELECTED));
          textAreaDefault.setEnabled(configuration.isMencoderIntelligentSync());
        }
      }
);
      JLabel label=new JLabel(Messages.getString("MEncoderVideo.33"));
      customPanel.add(label,BorderLayout.NORTH);
      customPanel.add(scrollPane,BorderLayout.SOUTH);
      codecPanel.add(intelligentsync,BorderLayout.NORTH);
      codecPanel.add(scrollPaneDefault,BorderLayout.CENTER);
      codecPanel.add(customPanel,BorderLayout.SOUTH);
      while (JOptionPane.showOptionDialog(SwingUtilities.getWindowAncestor((Component)PMS.get().getFrame()),codecPanel,Messages.getString("MEncoderVideo.34"),JOptionPane.OK_CANCEL_OPTION,JOptionPane.PLAIN_MESSAGE,null,null,null) == JOptionPane.OK_OPTION) {
        String newCodecparam=textArea.getText();
        DLNAMediaInfo fakemedia=new DLNAMediaInfo();
        DLNAMediaAudio audio=new DLNAMediaAudio();
        audio.setCodecA("ac3");
        fakemedia.setCodecV("mpeg4");
        fakemedia.setContainer("matroska");
        fakemedia.setDuration(45d * 60);
        audio.getAudioProperties().setNumberOfChannels(2);
        fakemedia.setWidth(1280);
        fakemedia.setHeight(720);
        audio.setSampleFrequency("48000");
        fakemedia.setFrameRate("23.976");
        fakemedia.getAudioTracksList().add(audio);
        String result[]=getSpecificCodecOptions(newCodecparam,fakemedia,new OutputParams(configuration),"dummy.mpg","dummy.srt",false,true);
        if (result.length > 0 && result[0].startsWith("@@")) {
          String errorMessage=result[0].substring(2);
          JOptionPane.showMessageDialog(SwingUtilities.getWindowAncestor((Component)PMS.get().getFrame()),errorMessage,Messages.getString("Dialog.Error"),JOptionPane.ERROR_MESSAGE);
        }
 else {
          configuration.setMencoderCodecSpecificConfig(newCodecparam);
          break;
        }
      }
    }
  }
);
  builder.add(button,FormLayoutUtil.flip(cc.xy(1,11),colSpec,orientation));
  forcefps=new JCheckBox(Messages.getString("MEncoderVideo.4"),configuration.isMencoderForceFps());
  forcefps.setContentAreaFilled(false);
  forcefps.addItemListener(new ItemListener(){
    @Override public void itemStateChanged(    ItemEvent e){
      configuration.setMencoderForceFps(e.getStateChange() == ItemEvent.SELECTED);
    }
  }
);
  builder.add(GuiUtil.getPreferredSizeComponent(forcefps),FormLayoutUtil.flip(cc.xyw(1,7,2),colSpec,orientation));
  yadif=new JCheckBox(Messages.getString("MEncoderVideo.26"),configuration.isMencoderYadif());
  yadif.setContentAreaFilled(false);
  yadif.addItemListener(new ItemListener(){
    @Override public void itemStateChanged(    ItemEvent e){
      configuration.setMencoderYadif(e.getStateChange() == ItemEvent.SELECTED);
    }
  }
);
  builder.add(GuiUtil.getPreferredSizeComponent(yadif),FormLayoutUtil.flip(cc.xyw(3,7,7),colSpec,orientation));
  scaler=new JCheckBox(Messages.getString("MEncoderVideo.27"));
  scaler.setContentAreaFilled(false);
  scaler.addItemListener(new ItemListener(){
    @Override public void itemStateChanged(    ItemEvent e){
      configuration.setMencoderScaler(e.getStateChange() == ItemEvent.SELECTED);
      scaleX.setEnabled(configuration.isMencoderScaler());
      scaleY.setEnabled(configuration.isMencoderScaler());
    }
  }
);
  builder.add(GuiUtil.getPreferredSizeComponent(scaler),FormLayoutUtil.flip(cc.xyw(3,5,6),colSpec,orientation));
  builder.addLabel(Messages.getString("MEncoderVideo.28"),FormLayoutUtil.flip(cc.xy(9,5,CellConstraints.RIGHT,CellConstraints.CENTER),colSpec,orientation));
  scaleX=new JTextField("" + configuration.getMencoderScaleX());
  scaleX.addKeyListener(new KeyAdapter(){
    @Override public void keyReleased(    KeyEvent e){
      try {
        configuration.setMencoderScaleX(Integer.parseInt(scaleX.getText()));
      }
 catch (      NumberFormatException nfe) {
        LOGGER.debug("Could not parse scaleX from \"" + scaleX.getText() + "\"");
      }
    }
  }
);
  builder.add(scaleX,FormLayoutUtil.flip(cc.xy(11,5),colSpec,orientation));
  builder.addLabel(Messages.getString("MEncoderVideo.30"),FormLayoutUtil.flip(cc.xy(13,5,CellConstraints.RIGHT,CellConstraints.CENTER),colSpec,orientation));
  scaleY=new JTextField("" + configuration.getMencoderScaleY());
  scaleY.addKeyListener(new KeyAdapter(){
    @Override public void keyReleased(    KeyEvent e){
      try {
        configuration.setMencoderScaleY(Integer.parseInt(scaleY.getText()));
      }
 catch (      NumberFormatException nfe) {
        LOGGER.debug("Could not parse scaleY from \"" + scaleY.getText() + "\"");
      }
    }
  }
);
  builder.add(scaleY,FormLayoutUtil.flip(cc.xy(15,5),colSpec,orientation));
  if (configuration.isMencoderScaler()) {
    scaler.setSelected(true);
  }
 else {
    scaleX.setEnabled(false);
    scaleY.setEnabled(false);
  }
  videoremux=new JCheckBox(Messages.getString("MEncoderVideo.38"),configuration.isMencoderMuxWhenCompatible());
  videoremux.setContentAreaFilled(false);
  videoremux.addItemListener(new ItemListener(){
    @Override public void itemStateChanged(    ItemEvent e){
      configuration.setMencoderMuxWhenCompatible((e.getStateChange() == ItemEvent.SELECTED));
    }
  }
);
  builder.add(GuiUtil.getPreferredSizeComponent(videoremux),FormLayoutUtil.flip(cc.xyw(1,9,13),colSpec,orientation));
  normalizeaudio=new JCheckBox(Messages.getString("MEncoderVideo.134"),configuration.isMEncoderNormalizeVolume());
  normalizeaudio.setContentAreaFilled(false);
  normalizeaudio.addItemListener(new ItemListener(){
    @Override public void itemStateChanged(    ItemEvent e){
      configuration.setMEncoderNormalizeVolume((e.getStateChange() == ItemEvent.SELECTED));
    }
  }
);
  builder.addLabel(Messages.getString("MEncoderVideo.6"),FormLayoutUtil.flip(cc.xy(1,15),colSpec,orientation));
  mencoder_custom_options=new JTextField(configuration.getMencoderCustomOptions());
  mencoder_custom_options.addKeyListener(new KeyAdapter(){
    @Override public void keyReleased(    KeyEvent e){
      configuration.setMencoderCustomOptions(mencoder_custom_options.getText());
    }
  }
);
  builder.add(mencoder_custom_options,FormLayoutUtil.flip(cc.xyw(3,15,13),colSpec,orientation));
  builder.addLabel(Messages.getString("MEncoderVideo.93"),FormLayoutUtil.flip(cc.xy(1,17),colSpec,orientation));
  builder.addLabel(Messages.getString("MEncoderVideo.28") + " (%)",FormLayoutUtil.flip(cc.xy(1,17,CellConstraints.RIGHT,CellConstraints.CENTER),colSpec,orientation));
  ocw=new JTextField(configuration.getMencoderOverscanCompensationWidth());
  ocw.addKeyListener(new KeyAdapter(){
    @Override public void keyReleased(    KeyEvent e){
      configuration.setMencoderOverscanCompensationWidth(ocw.getText());
    }
  }
);
  builder.add(ocw,FormLayoutUtil.flip(cc.xy(3,17),colSpec,orientation));
  builder.addLabel(Messages.getString("MEncoderVideo.30") + " (%)",FormLayoutUtil.flip(cc.xy(5,17),colSpec,orientation));
  och=new JTextField(configuration.getMencoderOverscanCompensationHeight());
  och.addKeyListener(new KeyAdapter(){
    @Override public void keyReleased(    KeyEvent e){
      configuration.setMencoderOverscanCompensationHeight(och.getText());
    }
  }
);
  builder.add(och,FormLayoutUtil.flip(cc.xy(7,17),colSpec,orientation));
  cmp=builder.addSeparator(Messages.getString("MEncoderVideo.8"),FormLayoutUtil.flip(cc.xyw(1,19,15),colSpec,orientation));
  cmp=(JComponent)cmp.getComponent(0);
  cmp.setFont(cmp.getFont().deriveFont(Font.BOLD));
  builder.addLabel(Messages.getString("MEncoderVideo.16"),FormLayoutUtil.flip(cc.xy(1,27),colSpec,orientation));
  builder.addLabel(Messages.getString("MEncoderVideo.133"),FormLayoutUtil.flip(cc.xy(1,27,CellConstraints.RIGHT,CellConstraints.CENTER),colSpec,orientation));
  mencoder_noass_scale=new JTextField(configuration.getMencoderNoAssScale());
  mencoder_noass_scale.addKeyListener(new KeyAdapter(){
    @Override public void keyReleased(    KeyEvent e){
      configuration.setMencoderNoAssScale(mencoder_noass_scale.getText());
    }
  }
);
  builder.addLabel(Messages.getString("MEncoderVideo.17"),FormLayoutUtil.flip(cc.xy(5,27),colSpec,orientation));
  mencoder_noass_outline=new JTextField(configuration.getMencoderNoAssOutline());
  mencoder_noass_outline.addKeyListener(new KeyAdapter(){
    @Override public void keyReleased(    KeyEvent e){
      configuration.setMencoderNoAssOutline(mencoder_noass_outline.getText());
    }
  }
);
  builder.addLabel(Messages.getString("MEncoderVideo.18"),FormLayoutUtil.flip(cc.xy(9,27),colSpec,orientation));
  mencoder_noass_blur=new JTextField(configuration.getMencoderNoAssBlur());
  mencoder_noass_blur.addKeyListener(new KeyAdapter(){
    @Override public void keyReleased(    KeyEvent e){
      configuration.setMencoderNoAssBlur(mencoder_noass_blur.getText());
    }
  }
);
  builder.addLabel(Messages.getString("MEncoderVideo.19"),FormLayoutUtil.flip(cc.xy(13,27),colSpec,orientation));
  mencoder_noass_subpos=new JTextField(configuration.getMencoderNoAssSubPos());
  mencoder_noass_subpos.addKeyListener(new KeyAdapter(){
    @Override public void keyReleased(    KeyEvent e){
      configuration.setMencoderNoAssSubPos(mencoder_noass_subpos.getText());
    }
  }
);
  builder.add(mencoder_noass_scale,FormLayoutUtil.flip(cc.xy(3,27),colSpec,orientation));
  builder.add(mencoder_noass_outline,FormLayoutUtil.flip(cc.xy(7,27),colSpec,orientation));
  builder.add(mencoder_noass_blur,FormLayoutUtil.flip(cc.xy(11,27),colSpec,orientation));
  builder.add(mencoder_noass_subpos,FormLayoutUtil.flip(cc.xy(15,27),colSpec,orientation));
  ass=new JCheckBox(Messages.getString("MEncoderVideo.20"),configuration.isMencoderAss());
  ass.setContentAreaFilled(false);
  ass.addItemListener(new ItemListener(){
    @Override public void itemStateChanged(    ItemEvent e){
      if (e != null) {
        configuration.setMencoderAss(e.getStateChange() == ItemEvent.SELECTED);
      }
    }
  }
);
  builder.add(GuiUtil.getPreferredSizeComponent(ass),FormLayoutUtil.flip(cc.xy(1,23),colSpec,orientation));
  ass.getItemListeners()[0].itemStateChanged(null);
  fc=new JCheckBox(Messages.getString("MEncoderVideo.21"),configuration.isMencoderFontConfig());
  fc.setContentAreaFilled(false);
  fc.addItemListener(new ItemListener(){
    @Override public void itemStateChanged(    ItemEvent e){
      configuration.setMencoderFontConfig(e.getStateChange() == ItemEvent.SELECTED);
    }
  }
);
  builder.add(GuiUtil.getPreferredSizeComponent(fc),FormLayoutUtil.flip(cc.xyw(3,23,5),colSpec,orientation));
  builder.addLabel(Messages.getString("MEncoderVideo.92"),FormLayoutUtil.flip(cc.xy(1,29),colSpec,orientation));
  subq=new JTextField(configuration.getMencoderVobsubSubtitleQuality());
  subq.addKeyListener(new KeyAdapter(){
    @Override public void keyReleased(    KeyEvent e){
      configuration.setMencoderVobsubSubtitleQuality(subq.getText());
    }
  }
);
  builder.add(subq,FormLayoutUtil.flip(cc.xyw(3,29,1),colSpec,orientation));
  configuration.addConfigurationListener(new ConfigurationListener(){
    @Override public void configurationChanged(    ConfigurationEvent event){
      if (event.getPropertyName() == null) {
        return;
      }
      if ((!event.isBeforeUpdate()) && event.getPropertyName().equals(PmsConfiguration.KEY_DISABLE_SUBTITLES)) {
        boolean enabled=!configuration.isDisableSubtitles();
        ass.setEnabled(enabled);
        fc.setEnabled(enabled);
        mencoder_noass_scale.setEnabled(enabled);
        mencoder_noass_outline.setEnabled(enabled);
        mencoder_noass_blur.setEnabled(enabled);
        mencoder_noass_subpos.setEnabled(enabled);
        ocw.setEnabled(enabled);
        och.setEnabled(enabled);
        subq.setEnabled(enabled);
        if (enabled) {
          ass.getItemListeners()[0].itemStateChanged(null);
        }
      }
    }
  }
);
  JPanel panel=builder.getPanel();
  panel.applyComponentOrientation(orientation);
  return panel;
}
