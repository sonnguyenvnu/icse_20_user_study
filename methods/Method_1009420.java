public void tree(String content,String type,String imagesDir,String imageTarget,String nodename){
  if (content.split("#").length > content.split("\\|-").length) {
    createFileTree(content,type,imagesDir,imageTarget,nodename);
  }
 else {
    createHighlightFileTree(content,type,imagesDir,imageTarget,nodename);
  }
}
