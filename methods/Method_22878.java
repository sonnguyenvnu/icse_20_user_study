public void addErrorTable(EditorFooter ef){
  JScrollPane scrollPane=new JScrollPane();
  errorTable=new ErrorTable(this);
  scrollPane.setBorder(BorderFactory.createEmptyBorder());
  scrollPane.setViewportView(errorTable);
  ef.addPanel(scrollPane,Language.text("editor.footer.errors"),"/lib/footer/error");
}
