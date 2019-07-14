protected File[] wrapFiles(String[] list){
  File[] outgoing=new File[list.length];
  for (int i=0; i < list.length; i++) {
    outgoing[i]=new File(libraryFolder,list[i]);
  }
  return outgoing;
}
