public void updateStateLazy(StateContainer.StateUpdate stateUpdate){
  final SectionTree sectionTree=mSectionTree;
  final Section section=mScope.get();
  sectionTree.updateStateLazy(section.getGlobalKey(),stateUpdate);
}
