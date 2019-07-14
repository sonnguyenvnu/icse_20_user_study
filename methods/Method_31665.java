public Set<String> findResourceNames(String location,URL locationUrl){
  String filePath=UrlUtils.toFilePath(locationUrl);
  String classPathRootOnDisk=filePath.substring(0,filePath.length() - location.length());
  if (!classPathRootOnDisk.endsWith("/")) {
    classPathRootOnDisk=classPathRootOnDisk + "/";
  }
  LOG.debug("Scanning starting at classpath root on JBoss VFS: " + classPathRootOnDisk);
  Set<String> resourceNames=new TreeSet<>();
  List<VirtualFile> files;
  try {
    files=VFS.getChild(filePath).getChildrenRecursively(new VirtualFileFilter(){
      public boolean accepts(      VirtualFile file){
        return file.isFile();
      }
    }
);
    for (    VirtualFile file : files) {
      resourceNames.add(file.getPathName().substring(classPathRootOnDisk.length()));
    }
  }
 catch (  IOException e) {
    LOG.warn("Unable to scan classpath root (" + classPathRootOnDisk + ") using JBoss VFS: " + e.getMessage());
  }
  return resourceNames;
}
