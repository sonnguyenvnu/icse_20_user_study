/** 
 * {@inheritDoc}
 */
@Override public boolean init(final String actionPath,final String[] separators){
  String prefix=separators[0];
  String split=separators[1];
  String suffix=separators[2];
  macrosCount=StringUtil.count(actionPath,prefix);
  if (macrosCount == 0) {
    return false;
  }
  names=new String[macrosCount];
  patterns=new String[macrosCount];
  fixed=new String[macrosCount + 1];
  int offset=0;
  int i=0;
  while (true) {
    int[] ndx=StringUtil.indexOfRegion(actionPath,prefix,suffix,offset);
    if (ndx == null) {
      break;
    }
    fixed[i]=actionPath.substring(offset,ndx[0]);
    String name=actionPath.substring(ndx[1],ndx[2]);
    String pattern=null;
    int colonNdx=name.indexOf(split);
    if (colonNdx != -1) {
      pattern=name.substring(colonNdx + 1).trim();
      name=name.substring(0,colonNdx).trim();
    }
    this.patterns[i]=pattern;
    this.names[i]=name;
    offset=ndx[3];
    i++;
  }
  if (offset < actionPath.length()) {
    fixed[i]=actionPath.substring(offset);
  }
 else {
    fixed[i]=StringPool.EMPTY;
  }
  return true;
}
