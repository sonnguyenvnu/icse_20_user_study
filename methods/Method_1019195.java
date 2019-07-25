@CliCommand(value="savepoint create",help="Savepoint a commit") public String savepoint(@CliOption(key={"commit"},help="Commit to savepoint") final String commitTime,@CliOption(key={"user"},help="User who is creating the savepoint") final String user,@CliOption(key={"comments"},help="Comments for creating the savepoint") final String comments) throws Exception {
  HoodieActiveTimeline activeTimeline=HoodieCLI.tableMetadata.getActiveTimeline();
  HoodieTimeline timeline=activeTimeline.getCommitTimeline().filterCompletedInstants();
  HoodieInstant commitInstant=new HoodieInstant(false,HoodieTimeline.COMMIT_ACTION,commitTime);
  if (!timeline.containsInstant(commitInstant)) {
    return "Commit " + commitTime + " not found in Commits " + timeline;
  }
  HoodieWriteClient client=createHoodieClient(null,HoodieCLI.tableMetadata.getBasePath());
  if (client.savepoint(commitTime,user,comments)) {
    refreshMetaClient();
    return String.format("The commit \"%s\" has been savepointed.",commitTime);
  }
  return String.format("Failed: Could not savepoint commit \"%s\".",commitTime);
}
