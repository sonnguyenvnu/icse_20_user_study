/** 
 * ?????????
 * @param content ??
 * @return ??
 */
public Vector query(String content){
  if (content == null || content.length() == 0)   return null;
  List<Term> termList=NotionalTokenizer.segment(content);
  Vector result=new Vector(dimension());
  int n=0;
  for (  Term term : termList) {
    Vector vector=wordVectorModel.vector(term.word);
    if (vector == null) {
      continue;
    }
    ++n;
    result.addToSelf(vector);
  }
  if (n == 0) {
    return null;
  }
  result.normalize();
  return result;
}
