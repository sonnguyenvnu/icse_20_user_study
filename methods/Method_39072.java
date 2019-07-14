/** 
 * Resolves result path.
 */
public ResultPath resolveResultPath(String path,String value){
  boolean absolutePath=false;
  if (value != null) {
    value=resolveAlias(value);
    if (StringUtil.startsWithChar(value,'/')) {
      absolutePath=true;
      int dotNdx=value.indexOf("..");
      if (dotNdx != -1) {
        path=value.substring(0,dotNdx);
        value=value.substring(dotNdx + 2);
      }
 else {
        path=value;
        value=null;
      }
    }
 else {
      int i=0;
      while (i < value.length()) {
        if (value.charAt(i) != '#') {
          break;
        }
        int dotNdx=MadvocUtil.lastIndexOfSlashDot(path);
        if (dotNdx != -1) {
          path=path.substring(0,dotNdx);
        }
        i++;
      }
      if (i > 0) {
        value=value.substring(i);
        if (StringUtil.startsWithChar(value,'.')) {
          value=value.substring(1);
        }
 else {
          int dotNdx=value.indexOf("..");
          if (dotNdx != -1) {
            path+='.' + value.substring(0,dotNdx);
            value=value.substring(dotNdx + 2);
          }
 else {
            if (value.length() > 0) {
              if (StringUtil.endsWithChar(path,'/')) {
                path+=value;
              }
 else {
                path+='.' + value;
              }
            }
            value=null;
          }
        }
      }
    }
  }
  if (!absolutePath) {
    if (resultPathPrefix != null) {
      path=resultPathPrefix + path;
    }
  }
  return new ResultPath(path,value);
}
