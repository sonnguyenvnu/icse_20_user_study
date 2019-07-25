protected JComponent config(String languageLabel){
  FormLayout layout=new FormLayout("left:pref, 3dlu, pref","p, 3dlu, p, 3dlu, p, 3dlu, p, 3dlu, p, 3dlu, p, 3dlu, p, 3dlu, p, 3dlu, p, 3dlu, p");
  PanelBuilder builder=new PanelBuilder(layout);
  builder.border(Borders.EMPTY);
  builder.opaque(false);
  CellConstraints cc=new CellConstraints();
  JComponent cmp=builder.addSeparator(Messages.getString(languageLabel),cc.xyw(1,1,1));
  cmp=(JComponent)cmp.getComponent(0);
  cmp.setFont(cmp.getFont().deriveFont(Font.BOLD));
  multithreading=new JCheckBox(Messages.getString("MEncoderVideo.35"),configuration.isFfmpegMultithreading());
  multithreading.setContentAreaFilled(false);
  multithreading.addItemListener(new ItemListener(){
    @Override public void itemStateChanged(    ItemEvent e){
      configuration.setFfmpegMultithreading(e.getStateChange() == ItemEvent.SELECTED);
    }
  }
);
  builder.add(GuiUtil.getPreferredSizeComponent(multithreading),cc.xy(1,3));
  videoRemuxTsMuxer=new JCheckBox(Messages.getString("MEncoderVideo.38"),configuration.isFFmpegMuxWithTsMuxerWhenCompatible());
  videoRemuxTsMuxer.setContentAreaFilled(false);
  videoRemuxTsMuxer.addItemListener(new ItemListener(){
    @Override public void itemStateChanged(    ItemEvent e){
      configuration.setFFmpegMuxWithTsMuxerWhenCompatible(e.getStateChange() == ItemEvent.SELECTED);
    }
  }
);
  builder.add(GuiUtil.getPreferredSizeComponent(videoRemuxTsMuxer),cc.xy(1,5));
  fc=new JCheckBox(Messages.getString("FFmpeg.3"),configuration.isFFmpegFontConfig());
  fc.setContentAreaFilled(false);
  fc.setToolTipText(Messages.getString("FFmpeg.0"));
  fc.addItemListener(new ItemListener(){
    @Override public void itemStateChanged(    ItemEvent e){
      configuration.setFFmpegFontConfig(e.getStateChange() == ItemEvent.SELECTED);
    }
  }
);
  builder.add(GuiUtil.getPreferredSizeComponent(fc),cc.xy(1,7));
  deferToMEncoderForSubtitles=new JCheckBox(Messages.getString("FFmpeg.1"),configuration.isFFmpegDeferToMEncoderForProblematicSubtitles());
  deferToMEncoderForSubtitles.setContentAreaFilled(false);
  deferToMEncoderForSubtitles.setToolTipText(Messages.getString("FFmpeg.2"));
  deferToMEncoderForSubtitles.addItemListener(new ItemListener(){
    @Override public void itemStateChanged(    ItemEvent e){
      configuration.setFFmpegDeferToMEncoderForProblematicSubtitles(e.getStateChange() == ItemEvent.SELECTED);
    }
  }
);
  builder.add(GuiUtil.getPreferredSizeComponent(deferToMEncoderForSubtitles),cc.xy(1,9));
  isFFmpegSoX=new JCheckBox(Messages.getString("FFmpeg.Sox"),configuration.isFFmpegSoX());
  isFFmpegSoX.setContentAreaFilled(false);
  isFFmpegSoX.setToolTipText(Messages.getString("FFmpeg.SoxTooltip"));
  isFFmpegSoX.addItemListener(new ItemListener(){
    @Override public void itemStateChanged(    ItemEvent e){
      configuration.setFFmpegSoX(e.getStateChange() == ItemEvent.SELECTED);
    }
  }
);
  builder.add(GuiUtil.getPreferredSizeComponent(isFFmpegSoX),cc.xy(1,11));
  builder.add(new JLabel(Messages.getString("FFmpeg.GPUDecodingAccelerationMethod")),cc.xy(1,13));
  String[] keys=configuration.getFFmpegAvailableGPUDecodingAccelerationMethods();
  FFmpegGPUDecodingAccelerationMethod=new JComboBox<>(keys);
  FFmpegGPUDecodingAccelerationMethod.setSelectedItem(configuration.getFFmpegGPUDecodingAccelerationMethod());
  FFmpegGPUDecodingAccelerationMethod.setToolTipText(Messages.getString("FFmpeg.GPUDecodingAccelerationMethodTooltip"));
  FFmpegGPUDecodingAccelerationMethod.addItemListener(new ItemListener(){
    @Override public void itemStateChanged(    ItemEvent e){
      if (e.getStateChange() == ItemEvent.SELECTED) {
        configuration.setFFmpegGPUDecodingAccelerationMethod((String)e.getItem());
      }
    }
  }
);
  FFmpegGPUDecodingAccelerationMethod.setEditable(true);
  builder.add(GuiUtil.getPreferredSizeComponent(FFmpegGPUDecodingAccelerationMethod),cc.xy(3,13));
  builder.addLabel(Messages.getString("FFmpeg.GPUDecodingThreadCount"),cc.xy(1,15));
  String[] threads=new String[]{"0","1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16"};
  FFmpegGPUDecodingAccelerationThreadNumber=new JComboBox<>(threads);
  FFmpegGPUDecodingAccelerationThreadNumber.setSelectedItem(configuration.getFFmpegGPUDecodingAccelerationThreadNumber());
  FFmpegGPUDecodingAccelerationThreadNumber.setToolTipText(Messages.getString("FFmpeg.GPUDecodingThreadCountTooltip"));
  FFmpegGPUDecodingAccelerationThreadNumber.addItemListener(new ItemListener(){
    @Override public void itemStateChanged(    ItemEvent e){
      configuration.setFFmpegGPUDecodingAccelerationThreadNumber((String)e.getItem());
    }
  }
);
  FFmpegGPUDecodingAccelerationThreadNumber.setEditable(true);
  builder.add(GuiUtil.getPreferredSizeComponent(FFmpegGPUDecodingAccelerationThreadNumber),cc.xy(3,15));
  return builder.getPanel();
}
