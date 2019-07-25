@Override public JComponent config(){
  ComponentOrientation orientation=ComponentOrientation.getOrientation(PMS.getLocale());
  String colSpec=FormLayoutUtil.getColSpec(COL_SPEC,orientation);
  FormLayout layout=new FormLayout(colSpec,ROW_SPEC);
  PanelBuilder builder=new PanelBuilder(layout);
  builder.border(Borders.EMPTY);
  builder.opaque(false);
  CellConstraints cc=new CellConstraints();
  JComponent cmp=builder.addSeparator(Messages.getString("NetworkTab.5"),FormLayoutUtil.flip(cc.xyw(1,1,5),colSpec,orientation));
  cmp=(JComponent)cmp.getComponent(0);
  cmp.setFont(cmp.getFont().deriveFont(Font.BOLD));
  experimentalCodecs=new JCheckBox(Messages.getString("VlcTrans.3"),configuration.isVlcExperimentalCodecs());
  experimentalCodecs.setContentAreaFilled(false);
  experimentalCodecs.addItemListener(new ItemListener(){
    @Override public void itemStateChanged(    ItemEvent e){
      configuration.setVlcExperimentalCodecs(e.getStateChange() == ItemEvent.SELECTED);
    }
  }
);
  builder.add(GuiUtil.getPreferredSizeComponent(experimentalCodecs),FormLayoutUtil.flip(cc.xy(1,3),colSpec,orientation));
  audioSyncEnabled=new JCheckBox(Messages.getString("MEncoderVideo.2"),configuration.isVlcAudioSyncEnabled());
  audioSyncEnabled.setContentAreaFilled(false);
  audioSyncEnabled.addItemListener(new ItemListener(){
    @Override public void itemStateChanged(    ItemEvent e){
      configuration.setVlcAudioSyncEnabled(e.getStateChange() == ItemEvent.SELECTED);
    }
  }
);
  builder.add(GuiUtil.getPreferredSizeComponent(audioSyncEnabled),FormLayoutUtil.flip(cc.xy(1,5),colSpec,orientation));
  JPanel panel=builder.getPanel();
  panel.applyComponentOrientation(orientation);
  return panel;
}
