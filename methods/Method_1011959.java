private void edit(){
  Library l=myListModel.get(myLibrariesList.getSelectedIndex());
  FileChooserDescriptor descriptor=new FileChooserDescriptor(false,true,true,true,false,false);
  final VirtualFile result=FileChooser.chooseFile(descriptor,myMainPanel,null,LocalFileSystem.getInstance().findFileByPath(l.getPath()));
  if (result == null) {
    return;
  }
  l.setPath(result.getPath());
  updateModel(true);
  myChanged=true;
}
