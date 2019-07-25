private DocServiceVfs vfs(int index){
  return (DocServiceVfs)((HttpFileService)serviceAt(index)).config().vfs();
}
