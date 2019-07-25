public void undo(final OsmQuest quest){
  if (quest == null)   return;
  if (quest.getStatus() == QuestStatus.ANSWERED || quest.getStatus() == QuestStatus.HIDDEN) {
    quest.setStatus(QuestStatus.NEW);
    quest.setChanges(null,null);
    osmQuestDB.update(quest);
    workerHandler.post(() -> relay.onQuestsCreated(Collections.singletonList(quest),QuestGroup.OSM));
  }
 else   if (quest.getStatus() == QuestStatus.CLOSED) {
    quest.setStatus(QuestStatus.REVERT);
    osmQuestDB.update(quest);
    OsmQuest reversedQuest=new OsmQuest(quest.getOsmElementQuestType(),quest.getElementType(),quest.getElementId(),quest.getGeometry());
    reversedQuest.setChanges(quest.getChanges().reversed(),quest.getChangesSource());
    reversedQuest.setStatus(QuestStatus.ANSWERED);
    undoOsmQuestDB.add(reversedQuest);
  }
 else {
    throw new IllegalStateException("Tried to undo a quest that hasn't been answered yet");
  }
}
