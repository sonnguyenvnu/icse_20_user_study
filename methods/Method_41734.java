protected void executePreProcessCommands(Scheduler scheduler) throws SchedulerException {
  for (  String group : jobGroupsToDelete) {
    if (group.equals("*")) {
      log.info("Deleting all jobs in ALL groups.");
      for (      String groupName : scheduler.getJobGroupNames()) {
        if (!jobGroupsToNeverDelete.contains(groupName)) {
          for (          JobKey key : scheduler.getJobKeys(GroupMatcher.jobGroupEquals(groupName))) {
            scheduler.deleteJob(key);
          }
        }
      }
    }
 else {
      if (!jobGroupsToNeverDelete.contains(group)) {
        log.info("Deleting all jobs in group: {}",group);
        for (        JobKey key : scheduler.getJobKeys(GroupMatcher.jobGroupEquals(group))) {
          scheduler.deleteJob(key);
        }
      }
    }
  }
  for (  String group : triggerGroupsToDelete) {
    if (group.equals("*")) {
      log.info("Deleting all triggers in ALL groups.");
      for (      String groupName : scheduler.getTriggerGroupNames()) {
        if (!triggerGroupsToNeverDelete.contains(groupName)) {
          for (          TriggerKey key : scheduler.getTriggerKeys(GroupMatcher.triggerGroupEquals(groupName))) {
            scheduler.unscheduleJob(key);
          }
        }
      }
    }
 else {
      if (!triggerGroupsToNeverDelete.contains(group)) {
        log.info("Deleting all triggers in group: {}",group);
        for (        TriggerKey key : scheduler.getTriggerKeys(GroupMatcher.triggerGroupEquals(group))) {
          scheduler.unscheduleJob(key);
        }
      }
    }
  }
  for (  JobKey key : jobsToDelete) {
    if (!jobGroupsToNeverDelete.contains(key.getGroup())) {
      log.info("Deleting job: {}",key);
      scheduler.deleteJob(key);
    }
  }
  for (  TriggerKey key : triggersToDelete) {
    if (!triggerGroupsToNeverDelete.contains(key.getGroup())) {
      log.info("Deleting trigger: {}",key);
      scheduler.unscheduleJob(key);
    }
  }
}
