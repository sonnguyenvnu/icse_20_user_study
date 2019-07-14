/** 
 * Generates a  {@link LayoutElementParcelable} adapted compatible element.Currently supports only local filesystem
 */
public LayoutElementParcelable generateLayoutElement(boolean showThumbs){
switch (mode) {
case FILE:
case ROOT:
    File file=new File(path);
  LayoutElementParcelable layoutElement;
if (isDirectory()) {
  layoutElement=new LayoutElementParcelable(path,RootHelper.parseFilePermission(file),"",folderSize() + "",0,true,file.lastModified() + "",false,showThumbs,mode);
}
 else {
  layoutElement=new LayoutElementParcelable(file.getPath(),RootHelper.parseFilePermission(file),file.getPath(),file.length() + "",file.length(),false,file.lastModified() + "",false,showThumbs,mode);
}
return layoutElement;
default :
return null;
}
}
