public ArrayList<LayoutElementParcelable> addToSmb(SmbFile[] mFile,String path,boolean showHiddenFiles) throws SmbException {
  ArrayList<LayoutElementParcelable> smbFileList=new ArrayList<>();
  if (searchHelper.size() > 500)   searchHelper.clear();
  for (  SmbFile aMFile : mFile) {
    if ((dataUtils.isFileHidden(aMFile.getPath()) || aMFile.isHidden()) && !showHiddenFiles) {
      continue;
    }
    String name=aMFile.getName();
    name=(aMFile.isDirectory() && name.endsWith("/")) ? name.substring(0,name.length() - 1) : name;
    if (path.equals(smbPath)) {
      if (name.endsWith("$"))       continue;
    }
    if (aMFile.isDirectory()) {
      folder_count++;
      LayoutElementParcelable layoutElement=new LayoutElementParcelable(name,aMFile.getPath(),"","","",0,false,aMFile.lastModified() + "",true,getBoolean(PREFERENCE_SHOW_THUMB),OpenMode.SMB);
      searchHelper.add(layoutElement.generateBaseFile());
      smbFileList.add(layoutElement);
    }
 else {
      file_count++;
      LayoutElementParcelable layoutElement=new LayoutElementParcelable(name,aMFile.getPath(),"","",Formatter.formatFileSize(getContext(),aMFile.length()),aMFile.length(),false,aMFile.lastModified() + "",false,getBoolean(PREFERENCE_SHOW_THUMB),OpenMode.SMB);
      layoutElement.setMode(OpenMode.SMB);
      searchHelper.add(layoutElement.generateBaseFile());
      smbFileList.add(layoutElement);
    }
  }
  return smbFileList;
}
