@Override public void update(ProgressMonitor monitor,@NotNull FileSystemEvent event){
  IFile mainFile=getFile();
  IFile backupFile=getBackupFile();
  boolean isChanged=false;
  for (  IFile file : event.getChanged()) {
    if (file.equals(mainFile) || file.equals(backupFile)) {
      isChanged=true;
      break;
    }
  }
  for (  IFile file : event.getRemoved()) {
    if (file.equals(backupFile)) {
      isChanged=true;
      break;
    }
  }
  if (isChanged) {
    fireChanged(monitor);
  }
}
