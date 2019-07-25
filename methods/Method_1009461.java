/** 
 * Make the given quest invisible (per user interaction). 
 */
public void hide(long questId,QuestGroup group){
  if (group == QuestGroup.OSM) {
    OsmQuest q=osmQuestDB.get(questId);
    if (q == null || q.getStatus() != QuestStatus.NEW)     return;
    q.setStatus(QuestStatus.HIDDEN);
    osmQuestDB.update(q);
    workerHandler.post(() -> relay.onQuestRemoved(q.getId(),group));
  }
 else   if (group == QuestGroup.OSM_NOTE) {
    OsmNoteQuest q=osmNoteQuestDB.get(questId);
    if (q == null || q.getStatus() != QuestStatus.NEW)     return;
    q.setStatus(QuestStatus.HIDDEN);
    osmNoteQuestDB.update(q);
    workerHandler.post(() -> relay.onQuestRemoved(q.getId(),group));
  }
}
