/** 
 * returns the object name (what comes before the '.') of the function starting at 'pos'
 */
static private String getObject(int pos,String code){
  boolean readObject=false;
  String obj="this";
  while (pos-- >= 0) {
    if (code.charAt(pos) == '.') {
      if (!readObject) {
        obj="";
        readObject=true;
      }
 else {
        break;
      }
    }
 else     if (code.charAt(pos) == ' ' || code.charAt(pos) == '\t') {
      break;
    }
 else     if (readObject) {
      obj=code.charAt(pos) + obj;
    }
  }
  return obj;
}
