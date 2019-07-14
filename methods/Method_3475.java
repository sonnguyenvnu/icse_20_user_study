/** 
 * ???????????????
 * @param goldFile
 * @param predFile
 * @return
 */
public static Result evaluate(String goldFile,String predFile,String dictPath) throws IOException {
  IOUtil.LineIterator goldIter=new IOUtil.LineIterator(goldFile);
  IOUtil.LineIterator predIter=new IOUtil.LineIterator(predFile);
  CWSEvaluator evaluator=new CWSEvaluator(dictPath);
  while (goldIter.hasNext() && predIter.hasNext()) {
    evaluator.compare(goldIter.next(),predIter.next());
  }
  return evaluator.getResult();
}
