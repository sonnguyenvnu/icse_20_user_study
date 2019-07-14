/** 
 * ??????????
 * @return
 */
public LexicalAnalyzer getAnalyzer(){
  for (  Pipe<List<IWord>,List<IWord>> pipe : this) {
    if (pipe instanceof LexicalAnalyzerPipe) {
      return ((LexicalAnalyzerPipe)pipe).analyzer;
    }
  }
  return null;
}
