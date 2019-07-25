public static TexGroup create(String name,GroupHierarchyType context,Path filePath,AuxParser auxParser,FileUpdateMonitor fileMonitor,MetaData metaData) throws IOException {
  TexGroup group=new TexGroup(name,context,filePath,auxParser,fileMonitor,metaData);
  fileMonitor.addListenerForFile(filePath,group);
  return group;
}
