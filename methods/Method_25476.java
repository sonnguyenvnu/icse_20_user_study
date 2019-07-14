private <M extends Suppressible,T extends Tree>VisitorState processMatchers(Iterable<M> matchers,T tree,TreeProcessor<M,T> processingFunction,VisitorState oldState){
  ErrorProneOptions errorProneOptions=oldState.errorProneOptions();
  for (  M matcher : matchers) {
    SuppressedState suppressed=isSuppressed(matcher,errorProneOptions);
    if (suppressed == SuppressedState.UNSUPPRESSED || errorProneOptions.isIgnoreSuppressionAnnotations()) {
      try (AutoCloseable unused=oldState.timingSpan(matcher)){
        VisitorState stateWithSuppressionInformation=oldState.withPathAndSuppression(getCurrentPath(),suppressed);
        reportMatch(processingFunction.process(matcher,tree,stateWithSuppressionInformation),stateWithSuppressionInformation);
      }
 catch (      Throwable t) {
        handleError(matcher,t);
      }
    }
  }
  return oldState.withPath(getCurrentPath());
}
