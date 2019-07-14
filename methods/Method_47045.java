/** 
 * Returns a path to parent for various  {@link #mode}
 * @deprecated use {@link #getParent(Context)} to handle content resolvers
 */
public String getParent(){
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
default :
StringBuilder builder=new StringBuilder(path);
return builder.substring(0,builder.length() - (getName().length() + 1));
}
return parentPath;
}
