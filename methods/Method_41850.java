/** 
 * <p> Resume (un-pause) all triggers - equivalent of calling <code>resumeTriggerGroup(group)</code> on every group. </p> <p> If any <code>Trigger</code> missed one or more fire-times, then the <code>Trigger</code>'s misfire instruction will be applied. </p>
 * @see #pauseAll()
 */
@Override public void resumeAll() throws JobPersistenceException {
  lock();
  try {
    jobFacade.clearPausedJobGroups();
    List<String> names=getTriggerGroupNames();
    for (    String name : names) {
      resumeTriggers(GroupMatcher.triggerGroupEquals(name));
    }
  }
  finally {
    unlock();
  }
}
