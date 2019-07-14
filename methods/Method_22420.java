public boolean addSketches(DefaultMutableTreeNode node,File folder,boolean examples) throws IOException {
  if (!folder.isDirectory()) {
    return false;
  }
  final String folderName=folder.getName();
  if (folderName.equals("libraries")) {
    return false;
  }
  if (!examples && folderName.equals("examples")) {
    return false;
  }
  String[] fileList=folder.list();
  if (fileList == null) {
    return false;
  }
  Arrays.sort(fileList,String.CASE_INSENSITIVE_ORDER);
  boolean found=false;
  for (  String name : fileList) {
    if (name.charAt(0) == '.') {
      continue;
    }
    File subfolder=new File(folder,name);
    if (subfolder.isDirectory()) {
      File entry=checkSketchFolder(subfolder,name);
      if (entry != null) {
        DefaultMutableTreeNode item=new DefaultMutableTreeNode(new SketchReference(name,entry));
        node.add(item);
        found=true;
      }
 else {
        DefaultMutableTreeNode subnode=new DefaultMutableTreeNode(name);
        boolean anything=addSketches(subnode,subfolder,examples);
        if (anything) {
          node.add(subnode);
          found=true;
        }
      }
    }
  }
  return found;
}
