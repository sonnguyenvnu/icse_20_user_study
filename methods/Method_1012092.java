private void populate(MPSTreeNode root,Iterable<DepLink> allDependencies){
  final TreeMessage DEPENDENCY_CYCLE=new TreeMessage(Color.RED,"dependency cycle",null);
  final TreeMessage HAS_CYCLE=new TreeMessage(new Color(128,0,0),"module with dependency cycle",null);
  final TreeMessage BOOTSTRAP_DEPENDENCY=new TreeMessage(Color.RED,"language with bootstrap dependency",null);
  Iterable<SModuleReference> sortedModules=Sequence.fromIterable(allDependencies).select(new ISelector<DepLink,SModuleReference>(){
    public SModuleReference select(    DepLink it){
      return it.module;
    }
  }
).distinct().sort(new ISelector<SModuleReference,String>(){
    public String select(    SModuleReference it){
      return it.getModuleName();
    }
  }
,true);
  final CycleBuilder cbDeps=new CycleBuilder(new Condition<DepLink>(){
    public boolean met(    DepLink dl){
      return dl.role.isDependency();
    }
  }
);
  for (  final SModuleReference module : Sequence.fromIterable(sortedModules)) {
    Iterable<DepLink> moduleDeps=Sequence.fromIterable(allDependencies).where(new IWhereFilter<DepLink>(){
      public boolean accept(      DepLink it){
        return module.equals(it.module) && it.role.isDependency();
      }
    }
).distinct();
    if (Sequence.fromIterable(moduleDeps).isEmpty()) {
      continue;
    }
    ModuleDependencyNode n=new ModuleDependencyNode(module,moduleDeps,false);
    n.updateIcon(myModule.getRepository());
    if (module.equals(myModule.getModuleReference())) {
      n.addTreeMessage(DEPENDENCY_CYCLE);
    }
 else {
      List<DepPath> cycles=Sequence.fromIterable(moduleDeps).translate(new ITranslator2<DepLink,DepPath>(){
        public Iterable<DepPath> translate(        DepLink dep){
          return cbDeps.cyclePaths(dep);
        }
      }
).toListSequence();
      if (ListSequence.fromList(cycles).isNotEmpty()) {
        n.setCycles(cycles);
        n.addTreeMessage(HAS_CYCLE);
      }
    }
    root.add(n);
  }
  if (isShowUsedLanguage()) {
    MPSTreeNode usedlanguages=new TextTreeNode("Used Languages");
    final CycleBuilder cbUsedLang=new CycleBuilder(new Condition<DepLink>(){
      public boolean met(      DepLink dl){
        return dl.role.isUsedLanguage();
      }
    }
);
    for (    final SModuleReference module : Sequence.fromIterable(sortedModules)) {
      Iterable<DepLink> usedLangDeps=Sequence.fromIterable(allDependencies).where(new IWhereFilter<DepLink>(){
        public boolean accept(        DepLink it){
          return it.module == module && it.role.isUsedLanguage();
        }
      }
);
      if (Sequence.fromIterable(usedLangDeps).isEmpty()) {
        continue;
      }
      ModuleDependencyNode n=new ModuleDependencyNode(module,usedLangDeps,true);
      n.updateIcon(myModule.getRepository());
      if (module.equals(myModule.getModuleReference())) {
        n.addTreeMessage(BOOTSTRAP_DEPENDENCY);
      }
 else {
        Iterable<DepPath> cycles=Sequence.fromIterable(usedLangDeps).translate(new ITranslator2<DepLink,DepPath>(){
          public Iterable<DepPath> translate(          DepLink dep){
            return cbUsedLang.cyclePaths(dep);
          }
        }
);
        if (Sequence.fromIterable(cycles).isNotEmpty()) {
          n.setCycles(cycles);
          n.addTreeMessage(BOOTSTRAP_DEPENDENCY);
        }
      }
      usedlanguages.add(n);
    }
    if (usedlanguages.getChildCount() > 0) {
      root.add(usedlanguages);
    }
  }
}
