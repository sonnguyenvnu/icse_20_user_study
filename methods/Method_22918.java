public List<Problem> findProblems(int line){
  int currentTab=getSketch().getCurrentCodeIndex();
  return problems.stream().filter(p -> p.getTabIndex() == currentTab).filter(p -> {
    int pStartLine=p.getLineNumber();
    int pEndOffset=p.getStopOffset();
    int pEndLine=textarea.getLineOfOffset(pEndOffset);
    return line >= pStartLine && line <= pEndLine;
  }
).collect(Collectors.toList());
}
