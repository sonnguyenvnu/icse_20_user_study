/** 
 * Parse the specified SVG matrix into a PMatrix2D. Note that PMatrix2D is rotated relative to the SVG definition, so parameters are rearranged here. More about the transformation matrices in <a href="http://www.w3.org/TR/SVG/coords.html#TransformAttribute">this section</a> of the SVG documentation.
 * @param matrixStr text of the matrix param.
 * @return a good old-fashioned PMatrix2D
 */
static protected PMatrix2D parseTransform(String matrixStr){
  matrixStr=matrixStr.trim();
  PMatrix2D outgoing=null;
  int start=0;
  int stop=-1;
  while ((stop=matrixStr.indexOf(')',start)) != -1) {
    PMatrix2D m=parseSingleTransform(matrixStr.substring(start,stop + 1));
    if (outgoing == null) {
      outgoing=m;
    }
 else {
      outgoing.apply(m);
    }
    start=stop + 1;
  }
  return outgoing;
}
