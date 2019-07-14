/** 
 * Returns indexes of the first string region. Region is defined by its left and right boundary. Return value is an array of the following indexes: <ul> <li>start of left boundary index</li> <li>region start index, i.e. end of left boundary</li> <li>region end index, i.e. start of right boundary</li> <li>end of right boundary index</li>  </ul> <p> Escape character may be used to prefix boundaries so they can be ignored. Double escaped region will be found, and first index of the result will be decreased to include one escape character.  If region is not founded, <code>null</code> is returned. 
 */
public static int[] indexOfRegion(final String string,final String leftBoundary,final String rightBoundary,final char escape,final int offset){
  int ndx=offset;
  int[] res=new int[4];
  while (true) {
    ndx=string.indexOf(leftBoundary,ndx);
    if (ndx == -1) {
      return null;
    }
    int leftBoundaryLen=leftBoundary.length();
    if (ndx > 0) {
      if (string.charAt(ndx - 1) == escape) {
        boolean cont=true;
        if (ndx > 1) {
          if (string.charAt(ndx - 2) == escape) {
            ndx--;
            leftBoundaryLen++;
            cont=false;
          }
        }
        if (cont) {
          ndx+=leftBoundaryLen;
          continue;
        }
      }
    }
    res[0]=ndx;
    ndx+=leftBoundaryLen;
    res[1]=ndx;
    while (true) {
      ndx=string.indexOf(rightBoundary,ndx);
      if (ndx == -1) {
        return null;
      }
      if (ndx > 0) {
        if (string.charAt(ndx - 1) == escape) {
          ndx+=rightBoundary.length();
          continue;
        }
      }
      res[2]=ndx;
      res[3]=ndx + rightBoundary.length();
      return res;
    }
  }
}
