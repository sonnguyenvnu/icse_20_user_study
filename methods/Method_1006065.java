private void init(){
  entries.setSelected(true);
  entries.setText(Localization.lang("Import entries"));
  strings.setSelected(true);
  strings.setText(Localization.lang("Import strings"));
  groups.setText(Localization.lang("Import group definitions"));
  selector.setText(Localization.lang("Import word selector definitions"));
  GridPane container=new GridPane();
  getDialogPane().setContent(container);
  container.setHgap(10);
  container.setVgap(10);
  container.add(entries,0,0);
  container.add(strings,0,1);
  container.add(groups,0,2);
  container.add(selector,0,3);
  container.setPadding(new Insets(15,5,0,5));
  container.setGridLinesVisible(false);
}
