/** 
 * {@inheritDoc}
 */
@Override protected void initialize(final WebTextField editor){
  this.editorComponent=editor;
  this.clickCountToStart=3;
  this.autoUpdateLeadingIcon=false;
  this.delegate=new FileNameEditorDelegate();
  editor.addActionListener(delegate);
}
