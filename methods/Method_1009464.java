/** 
 * Sort given list by the user defined order 
 */
public synchronized void sort(List<QuestType> questTypes){
  List<List<QuestType>> orderLists=getAsQuestTypeLists();
  for (  List<QuestType> list : orderLists) {
    List<QuestType> reorderedQuestTypes=new ArrayList<>(list.size() - 1);
    for (    QuestType questType : list.subList(1,list.size())) {
      if (questTypes.remove(questType)) {
        reorderedQuestTypes.add(questType);
      }
    }
    int startIndex=questTypes.indexOf(list.get(0));
    questTypes.addAll(startIndex + 1,reorderedQuestTypes);
  }
}
