public static Iterable<SModule> count(Iterable<SModule> initial){
  Iterable<SModule> modules=GlobalScope.getInstance().getModules();
  Map<SModuleReference,List<Tuples._2<Boolean,SModule>>> reverseDependency=MapSequence.fromMap(new HashMap<SModuleReference,List<Tuples._2<Boolean,SModule>>>());
  for (  SModule module : Sequence.fromIterable(modules)) {
    for (    SDependency dependency : Sequence.fromIterable(module.getDeclaredDependencies())) {
      SModuleReference targetModule=dependency.getTargetModule();
      if (!(MapSequence.fromMap(reverseDependency).containsKey(targetModule))) {
        MapSequence.fromMap(reverseDependency).put(targetModule,ListSequence.fromList(new ArrayList<Tuples._2<Boolean,SModule>>()));
      }
      ListSequence.fromList(MapSequence.fromMap(reverseDependency).get(targetModule)).addElement(MultiTuple.<Boolean,SModule>from(dependency.isReexport(),module));
    }
  }
  Queue<SModule> q=QueueSequence.fromQueueWithValues(new LinkedList<SModule>(),initial);
  Set<SModule> engaged=SetSequence.fromSetWithValues(new HashSet<SModule>(),initial);
  Set<SModule> checked=SetSequence.fromSet(new HashSet<SModule>());
  while (QueueSequence.fromQueue(q).isNotEmpty()) {
    SModule provider=QueueSequence.fromQueue(q).removeFirstElement();
    if (SetSequence.fromSet(checked).contains(provider)) {
      continue;
    }
 else {
      SetSequence.fromSet(checked).addElement(provider);
    }
    for (    Tuples._2<Boolean,SModule> dependency : ListSequence.fromList(MapSequence.fromMap(reverseDependency).get(provider.getModuleReference()))) {
      SetSequence.fromSet(engaged).addElement(dependency._1());
      if ((boolean)dependency._0()) {
        QueueSequence.fromQueue(q).addLastElement(dependency._1());
      }
    }
  }
  return engaged;
}
