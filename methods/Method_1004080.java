/** 
 * Create a IFrame instance based on the given analyzer.
 * @param analyzer analyzer instance or <code>null</code>
 * @param popCount number of items to remove from the operand stack
 * @return IFrame instance. In case the analyzer is <code>null</code> ordoes not contain stackmap information a "NOP" IFrame is returned.
 */
static IFrame create(final AnalyzerAdapter analyzer,final int popCount){
  if (analyzer == null || analyzer.locals == null) {
    return NOP;
  }
  final Object[] locals=reduce(analyzer.locals,0);
  final Object[] stack=reduce(analyzer.stack,popCount);
  return new FrameSnapshot(locals,stack);
}
