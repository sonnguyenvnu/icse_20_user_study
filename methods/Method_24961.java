/** 
 * Builds an int array for every tab that represents the scope depth at each character.
 */
static private int[] getCurlyScopes(String code){
  List<Range> comments=getCommentBlocks(code);
  int[] scopes=new int[code.length()];
  int curlyScope=0;
  boolean arrayAssignmentMaybeCommingFlag=false;
  int arrayAssignmentCurlyScope=0;
  for (int pos=0; pos < code.length(); pos++) {
    scopes[pos]=curlyScope;
    if (isInRangeList(pos,comments)) {
      continue;
    }
    if (code.charAt(pos) == '{') {
      if (arrayAssignmentMaybeCommingFlag || arrayAssignmentCurlyScope > 0) {
        arrayAssignmentCurlyScope++;
        arrayAssignmentMaybeCommingFlag=false;
      }
 else {
        curlyScope++;
      }
    }
 else     if (code.charAt(pos) == '}') {
      if (arrayAssignmentCurlyScope > 0) {
        arrayAssignmentCurlyScope--;
      }
 else {
        curlyScope--;
      }
    }
 else     if (code.charAt(pos) == '=') {
      arrayAssignmentMaybeCommingFlag=true;
    }
 else     if (!isWhiteSpace(code.charAt(pos))) {
      arrayAssignmentMaybeCommingFlag=false;
    }
  }
  return scopes;
}
