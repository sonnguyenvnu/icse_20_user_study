@Override public JComponent config(){
  ComponentOrientation orientation=ComponentOrientation.getOrientation(PMS.getLocale());
  String colSpec=FormLayoutUtil.getColSpec(COL_SPEC,orientation);
  FormLayout layout=new FormLayout(colSpec,ROW_SPEC);
  PanelBuilder builder=new PanelBuilder(layout);
  builder.border(Borders.EMPTY);
  builder.opaque(false);
  CellConstraints cc=new CellConstraints();
  JComponent cmp=builder.addSeparator(Messages.getString("NetworkTab.5"),FormLayoutUtil.flip(cc.xyw(2,1,1),colSpec,orientation));
  cmp=(JComponent)cmp.getComponent(0);
  cmp.setFont(cmp.getFont().deriveFont(Font.BOLD));
  tsmuxerforcefps=new JCheckBox(Messages.getString("TsMuxeRVideo.2"),configuration.isTsmuxerForceFps());
  tsmuxerforcefps.setContentAreaFilled(false);
  tsmuxerforcefps.addItemListener(new ItemListener(){
    @Override public void itemStateChanged(    ItemEvent e){
      configuration.setTsmuxerForceFps(e.getStateChange() == ItemEvent.SELECTED);
    }
  }
);
  builder.add(GuiUtil.getPreferredSizeComponent(tsmuxerforcefps),FormLayoutUtil.flip(cc.xy(2,3),colSpec,orientation));
  muxallaudiotracks=new JCheckBox(Messages.getString("TsMuxeRVideo.19"),configuration.isMuxAllAudioTracks());
  muxallaudiotracks.setContentAreaFilled(false);
  muxallaudiotracks.addItemListener(new ItemListener(){
    @Override public void itemStateChanged(    ItemEvent e){
      configuration.setMuxAllAudioTracks(e.getStateChange() == ItemEvent.SELECTED);
    }
  }
);
  builder.add(GuiUtil.getPreferredSizeComponent(muxallaudiotracks),FormLayoutUtil.flip(cc.xy(2,5),colSpec,orientation));
  JPanel panel=builder.getPanel();
  panel.applyComponentOrientation(orientation);
  return panel;
}
