/** 
 * @param insertIdx the index to be inserted
 * @param groups a group list
 * @since 2.1.0
 */
public void insertBatchWith(int insertIdx,List<Card> groups){
  VirtualLayoutManager layoutManager=getLayoutManager();
  if (groups != null && groups.size() > 0 && mGroupBasicAdapter != null && layoutManager != null) {
    List<LayoutHelper> layoutHelpers=layoutManager.getLayoutHelpers();
    final List<LayoutHelper> newLayoutHelpers=new ArrayList<>(layoutHelpers);
    List<LayoutHelper> insertedLayoutHelpers=new ArrayList<>();
    for (int i=0, size=groups.size(); i < size; i++) {
      insertedLayoutHelpers.add(groups.get(i).getLayoutHelper());
    }
    if (insertIdx >= layoutHelpers.size()) {
      newLayoutHelpers.addAll(insertedLayoutHelpers);
    }
 else {
      newLayoutHelpers.addAll(insertIdx,insertedLayoutHelpers);
    }
    layoutManager.setLayoutHelpers(newLayoutHelpers);
    mGroupBasicAdapter.insertBatchComponents(insertIdx,groups);
  }
}
