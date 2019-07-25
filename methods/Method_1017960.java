/** 
 * Visit this process group using the supplied visitor
 * @param nifiVisitor the visitor
 */
@Override public void accept(NifiFlowVisitor nifiVisitor){
  if (dto.getContents() != null) {
    if (dto.getContents().getProcessors() != null) {
      for (      ProcessorDTO processorDTO : dto.getContents().getProcessors()) {
        NifiVisitableProcessor processor=getOrCreateProcessor(nifiVisitor,processorDTO);
        nifiVisitor.visitProcessor(processor);
      }
    }
    if (dto.getContents().getProcessGroups() != null) {
      for (      ProcessGroupDTO processGroupDTO : dto.getContents().getProcessGroups()) {
        if (processGroupDTO != null) {
          nifiVisitor.visitProcessGroup(getOrCreateProcessGroup(nifiVisitor,processGroupDTO));
        }
      }
    }
    if (dto.getContents().getConnections() != null) {
      for (      ConnectionDTO connectionDTO : dto.getContents().getConnections()) {
        nifiVisitor.visitConnection(new NifiVisitableConnection(this,connectionDTO));
      }
    }
    populateStartingAndEndingProcessors();
  }
}
