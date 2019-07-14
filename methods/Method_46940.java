boolean checkFiles(HybridFile hFile1,HybridFile hFile2) throws ShellNotRunningException {
  if (RootHelper.isDirectory(hFile1.getPath(),isRootExplorer,5)) {
    if (RootHelper.fileExists(hFile2.getPath()))     return false;
    ArrayList<HybridFileParcelable> baseFiles=RootHelper.getFilesList(hFile1.getPath(),true,true,null);
    if (baseFiles.size() > 0) {
      boolean b=true;
      for (      HybridFileParcelable baseFile : baseFiles) {
        if (!checkFiles(new HybridFile(baseFile.getMode(),baseFile.getPath()),new HybridFile(hFile2.getMode(),hFile2.getPath() + "/" + (baseFile.getName()))))         b=false;
      }
      return b;
    }
    return RootHelper.fileExists(hFile2.getPath());
  }
 else {
    ArrayList<HybridFileParcelable> baseFiles=RootHelper.getFilesList(hFile1.getParent(),true,true,null);
    int i=-1;
    int index=-1;
    for (    HybridFileParcelable b : baseFiles) {
      i++;
      if (b.getPath().equals(hFile1.getPath())) {
        index=i;
        break;
      }
    }
    ArrayList<HybridFileParcelable> baseFiles1=RootHelper.getFilesList(hFile1.getParent(),true,true,null);
    int i1=-1;
    int index1=-1;
    for (    HybridFileParcelable b : baseFiles1) {
      i1++;
      if (b.getPath().equals(hFile1.getPath())) {
        index1=i1;
        break;
      }
    }
    return baseFiles.get(index).getSize() == baseFiles1.get(index1).getSize();
  }
}
