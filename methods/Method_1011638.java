public void sort(){
  int count=0;
  SubTaskOrderHelper.SubTask[] subtasks=new SubTaskOrderHelper.SubTask[ListSequence.fromList(list).count()];
  for (  SNode st : list) {
    SubTaskOrderHelper.SubTask wrapper=new SubTaskOrderHelper.SubTask(st,count);
    map.put(st,wrapper);
    subtasks[count++]=wrapper;
  }
  for (  SubTaskOrderHelper.SubTask st : subtasks) {
    for (    SNode dep : SLinkOperations.getChildren(st.getTask(),MetaAdapterFactory.getContainmentLink(0x698a8d22a10447a0L,0xba8d10e3ec237f13L,0x2670d5989d5a6275L,0x2670d5989d5b4a01L,"after"))) {
      SubTaskOrderHelper.SubTask afterTask=map.get(SLinkOperations.getTarget(dep,MetaAdapterFactory.getReferenceLink(0x698a8d22a10447a0L,0xba8d10e3ec237f13L,0x2670d5989d5b49b8L,0x2670d5989d5b49b9L,"target")));
      if (afterTask == null) {
        genContext.showErrorMessage(dep,"dependency on non-existing subtask");
        continue;
      }
      st.targets.add(afterTask.getIndex());
    }
    for (    SNode dep : SLinkOperations.getChildren(st.getTask(),MetaAdapterFactory.getContainmentLink(0x698a8d22a10447a0L,0xba8d10e3ec237f13L,0x2670d5989d5a6275L,0x36fb0dc9fd36bb1bL,"before"))) {
      SubTaskOrderHelper.SubTask beforeTask=map.get(SLinkOperations.getTarget(dep,MetaAdapterFactory.getReferenceLink(0x698a8d22a10447a0L,0xba8d10e3ec237f13L,0x2670d5989d5b49b8L,0x2670d5989d5b49b9L,"target")));
      if (beforeTask == null) {
        genContext.showErrorMessage(dep,"dependency on non-existing subtask");
        continue;
      }
      beforeTask.targets.add(st.getIndex());
    }
  }
  int[][] graph=new int[count][];
  for (  SubTaskOrderHelper.SubTask st : subtasks) {
    graph[st.getIndex()]=st.getTargets();
  }
  int[][] partitions=GraphUtil.tarjan(graph);
  ListSequence.fromList(list).clear();
  for (  int[] cycle : partitions) {
    if (cycle.length > 1) {
      StringBuilder sb=new StringBuilder();
      sb.append("subtasks cycle detected: ");
      for (int i=0; i < 5 && i < cycle.length; i++) {
        if (i > 0) {
          sb.append(", ");
        }
        sb.append(SPropertyOperations.getString(subtasks[cycle[i]].getTask(),MetaAdapterFactory.getProperty(0xceab519525ea4f22L,0x9b92103b95ca8c0cL,0x110396eaaa4L,0x110396ec041L,"name")));
      }
      if (cycle.length > 5) {
        sb.append(" ...");
      }
      genContext.showErrorMessage(subtasks[cycle[0]].getTask(),sb.toString());
      continue;
    }
    ListSequence.fromList(list).addElement(subtasks[cycle[0]].getTask());
  }
}
