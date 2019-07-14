public void initializeFromConfig(Project project,SortingConfig config){
  _criteria=config.getCriteria();
  int count=_criteria.length;
  _keyMakers=new KeyMaker[count];
  _comparatorWrappers=new ComparatorWrapper[count];
  for (int i=0; i < count; i++) {
    _keyMakers[i]=_criteria[i].createKeyMaker();
    _comparatorWrappers[i]=new ComparatorWrapper(i);
  }
}
