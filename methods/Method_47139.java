private LayoutElementParcelable addTo(HybridFileParcelable mFile){
  File f=new File(mFile.getPath());
  String size="";
  if (!dataUtils.isFileHidden(mFile.getPath())) {
    if (mFile.isDirectory()) {
      size="";
      LayoutElementParcelable layoutElement=new LayoutElementParcelable(f.getPath(),mFile.getPermission(),mFile.getLink(),size,0,true,mFile.getDate() + "",false,getBoolean(PREFERENCE_SHOW_THUMB),mFile.getMode());
      LIST_ELEMENTS.add(layoutElement);
      folder_count++;
      return layoutElement;
    }
 else {
      long longSize=0;
      try {
        if (mFile.getSize() != -1) {
          longSize=mFile.getSize();
          size=Formatter.formatFileSize(getContext(),longSize);
        }
 else {
          size="";
          longSize=0;
        }
      }
 catch (      NumberFormatException e) {
      }
      try {
        LayoutElementParcelable layoutElement=new LayoutElementParcelable(f.getPath(),mFile.getPermission(),mFile.getLink(),size,longSize,false,mFile.getDate() + "",false,getBoolean(PREFERENCE_SHOW_THUMB),mFile.getMode());
        LIST_ELEMENTS.add(layoutElement);
        file_count++;
        return layoutElement;
      }
 catch (      Exception e) {
        e.printStackTrace();
      }
    }
  }
  return null;
}
