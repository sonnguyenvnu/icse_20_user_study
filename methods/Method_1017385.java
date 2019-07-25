private void init(){
  this.field=new EditorTextField("",project,com.jetbrains.php.lang.PhpFileType.INSTANCE);
  PhpCompletionUtil.installClassCompletion(this.field,null,getDisposable());
  this.field.getDocument().addDocumentListener(new DocumentAdapter(){
    @Override public void documentChanged(    DocumentEvent e){
      String text=field.getText();
      if (StringUtil.isEmpty(text) || StringUtil.endsWith(text,"\\")) {
        return;
      }
      addUpdateRequest(250,() -> consumer.consume(field.getText()));
    }
  }
);
  GridBagConstraints gbConstraints=new GridBagConstraints();
  gbConstraints.fill=1;
  gbConstraints.weightx=1.0D;
  gbConstraints.gridx=1;
  gbConstraints.gridy=1;
  panel.add(field,gbConstraints);
}
