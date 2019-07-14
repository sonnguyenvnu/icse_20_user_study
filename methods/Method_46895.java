private LayoutElementParcelable createListParcelables(HybridFileParcelable baseFile){
  if (!dataUtils.isFileHidden(baseFile.getPath())) {
    String size="";
    long longSize=0;
    if (baseFile.isDirectory()) {
      ma.folder_count++;
    }
 else {
      if (baseFile.getSize() != -1) {
        try {
          longSize=baseFile.getSize();
          size=Formatter.formatFileSize(c,longSize);
        }
 catch (        NumberFormatException e) {
          e.printStackTrace();
        }
      }
      ma.file_count++;
    }
    LayoutElementParcelable layoutElement=new LayoutElementParcelable(baseFile.getName(),baseFile.getPath(),baseFile.getPermission(),baseFile.getLink(),size,longSize,false,baseFile.getDate() + "",baseFile.isDirectory(),showThumbs,baseFile.getMode());
    return layoutElement;
  }
  return null;
}
