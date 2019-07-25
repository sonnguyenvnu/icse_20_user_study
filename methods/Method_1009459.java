/** 
 * Apply the user's answer to the given quest. (The quest will turn invisible.)
 * @return true if successful 
 */
public boolean solve(long questId,QuestGroup group,Object answer,String source){
  boolean success=false;
  if (group == QuestGroup.OSM) {
    success=solveOsmQuest(questId,answer,source);
  }
 else   if (group == QuestGroup.OSM_NOTE) {
    success=solveOsmNoteQuest(questId,answer);
  }
  workerHandler.post(() -> relay.onQuestRemoved(questId,group));
  return success;
}
