public void init(String torrentId,ArrayList<BencodeFileItem> files,List<Priority> priorities){
  if (fileTree != null)   return;
  if (torrentId == null || files == null || priorities == null)   return;
  this.torrentId=torrentId;
  this.files=files;
  this.priorities=new ArrayList<>();
  for (  Priority priority : priorities)   this.priorities.add(new FilePriority(priority));
  makeFileTree();
  updateFileSize();
  reloadData();
}
