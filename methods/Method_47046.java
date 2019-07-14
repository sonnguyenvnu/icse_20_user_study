/** 
 * Helper method to get parent path
 */
public String getParent(Context context){
  String parentPath="";
switch (mode) {
case SMB:
    try {
      parentPath=new SmbFile(path).getParent();
    }
 catch (    MalformedURLException e) {
      parentPath="";
      e.printStackTrace();
    }
  break;
case FILE:
case ROOT:
parentPath=new File(path).getParent();
break;
case OTG:
default :
StringBuilder builder=new StringBuilder(path);
StringBuilder parentPathBuilder=new StringBuilder(builder.substring(0,builder.length() - (getName(context).length() + 1)));
return parentPathBuilder.toString();
}
return parentPath;
}
