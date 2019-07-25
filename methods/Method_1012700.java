@Override protected JComponent config(String languageLabel){
  FormLayout layout=new FormLayout("left:pref, 0:grow","p, 3dlu, p, 3dlu, p, 3dlu, p, 3dlu, p, 3dlu");
  PanelBuilder builder=new PanelBuilder(layout);
  builder.border(Borders.EMPTY);
  builder.opaque(false);
  CellConstraints cc=new CellConstraints();
  JComponent cmp=builder.addSeparator(Messages.getString(languageLabel),cc.xyw(2,1,1));
  cmp=(JComponent)cmp.getComponent(0);
  cmp.setFont(cmp.getFont().deriveFont(Font.BOLD));
  multithreading=new JCheckBox(Messages.getString("MEncoderVideo.35"),configuration.isFfmpegAviSynthMultithreading());
  multithreading.setContentAreaFilled(false);
  multithreading.addItemListener(new ItemListener(){
    @Override public void itemStateChanged(    ItemEvent e){
      configuration.setFfmpegAviSynthMultithreading(e.getStateChange() == ItemEvent.SELECTED);
    }
  }
);
  builder.add(GuiUtil.getPreferredSizeComponent(multithreading),cc.xy(2,3));
  interframe=new JCheckBox(Messages.getString("AviSynthMEncoder.13"),configuration.getFfmpegAvisynthInterFrame());
  interframe.setContentAreaFilled(false);
  interframe.addActionListener(new ActionListener(){
    @Override public void actionPerformed(    ActionEvent e){
      configuration.setFfmpegAvisynthInterFrame(interframe.isSelected());
      if (configuration.getFfmpegAvisynthInterFrame()) {
        JOptionPane.showMessageDialog(SwingUtilities.getWindowAncestor((Component)PMS.get().getFrame()),Messages.getString("AviSynthMEncoder.16"),Messages.getString("Dialog.Information"),JOptionPane.INFORMATION_MESSAGE);
      }
    }
  }
);
  builder.add(GuiUtil.getPreferredSizeComponent(interframe),cc.xy(2,5));
  interframegpu=new JCheckBox(Messages.getString("AviSynthMEncoder.15"),configuration.getFfmpegAvisynthInterFrameGPU());
  interframegpu.setContentAreaFilled(false);
  interframegpu.addItemListener(new ItemListener(){
    @Override public void itemStateChanged(    ItemEvent e){
      configuration.setFfmpegAvisynthInterFrameGPU((e.getStateChange() == ItemEvent.SELECTED));
    }
  }
);
  builder.add(GuiUtil.getPreferredSizeComponent(interframegpu),cc.xy(2,7));
  convertfps=new JCheckBox(Messages.getString("AviSynthMEncoder.3"),configuration.getFfmpegAvisynthConvertFps());
  convertfps.setContentAreaFilled(false);
  convertfps.addItemListener(new ItemListener(){
    @Override public void itemStateChanged(    ItemEvent e){
      configuration.setFfmpegAvisynthConvertFps((e.getStateChange() == ItemEvent.SELECTED));
    }
  }
);
  builder.add(GuiUtil.getPreferredSizeComponent(convertfps),cc.xy(2,9));
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
