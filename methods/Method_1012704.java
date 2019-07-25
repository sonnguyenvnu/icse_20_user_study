@Override public JComponent config(){
  FormLayout layout=new FormLayout("left:pref, 0:grow","p, 3dlu, p, 3dlu, p, 3dlu, p, 3dlu, p, 3dlu, 0:grow");
  PanelBuilder builder=new PanelBuilder(layout);
  builder.border(Borders.EMPTY);
  builder.opaque(false);
  CellConstraints cc=new CellConstraints();
  JComponent cmp=builder.addSeparator(Messages.getString("NetworkTab.5"),cc.xyw(2,1,1));
  cmp=(JComponent)cmp.getComponent(0);
  cmp.setFont(cmp.getFont().deriveFont(Font.BOLD));
  noresample=new JCheckBox(Messages.getString("TrTab2.22"),configuration.isAudioResample());
  noresample.setContentAreaFilled(false);
  noresample.addItemListener(new ItemListener(){
    @Override public void itemStateChanged(    ItemEvent e){
      configuration.setAudioResample(e.getStateChange() == ItemEvent.SELECTED);
    }
  }
);
  builder.add(GuiUtil.getPreferredSizeComponent(noresample),cc.xy(2,3));
  return builder.getPanel();
}
