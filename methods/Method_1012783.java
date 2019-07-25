public JComponent build(){
  ComponentOrientation orientation=ComponentOrientation.getOrientation(PMS.getLocale());
  String colSpec=FormLayoutUtil.getColSpec(PANEL_COL_SPEC,orientation);
  FormLayout layout=new FormLayout(colSpec,PANEL_ROW_SPEC);
  PanelBuilder builder=new PanelBuilder(layout);
  builder.border(Borders.DLU4);
  builder.opaque(true);
  CellConstraints cc=new CellConstraints();
  sharedFoldersPanel=initSharedFoldersGuiComponents(cc).build();
  sharedWebContentPanel=initWebContentGuiComponents(cc).build();
  String webConfPath=configuration.getWebConfPath();
  File webConf=new File(webConfPath);
  if (webConf.exists() && configuration.getExternalNetwork()) {
    parseWebConf(webConf);
  }
  builder.add(sharedFoldersPanel,FormLayoutUtil.flip(cc.xyw(1,1,12),colSpec,orientation));
  builder.add(sharedWebContentPanel,FormLayoutUtil.flip(cc.xyw(1,3,12),colSpec,orientation));
  JPanel panel=builder.getPanel();
  panel.applyComponentOrientation(orientation);
  JScrollPane scrollPane=new JScrollPane(panel,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
  scrollPane.setBorder(BorderFactory.createEmptyBorder());
  return scrollPane;
}
