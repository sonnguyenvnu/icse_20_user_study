/** 
 * Returns <code>true</code> if path matches the query.
 */
public boolean matches(final Path path){
  int exprNdx=0;
  int pathNdx=0;
  int pathLen=path.length();
  int exprLen=expression.length;
  while (pathNdx < pathLen) {
    CharSequence current=path.get(pathNdx);
    if (exprNdx < exprLen && expression[exprNdx].equals(STAR)) {
      exprNdx++;
    }
 else     if (exprNdx < exprLen && expression[exprNdx].contentEquals(current)) {
      pathNdx++;
      exprNdx++;
    }
 else     if (exprNdx - 1 >= 0 && expression[exprNdx - 1].equals(STAR)) {
      pathNdx++;
    }
 else {
      return false;
    }
  }
  if (exprNdx > 0 && expression[exprNdx - 1].equals(STAR)) {
    return pathNdx >= pathLen && exprNdx >= exprLen;
  }
 else {
    return pathLen != 0 && pathNdx >= pathLen && (included || exprNdx >= exprLen);
  }
}
