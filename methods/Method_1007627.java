@Command(name="sel",desc="Choose the snapshot based on the list id") @CommandPermissions("worldedit.snapshots.restore") public void sel(Player player,LocalSession session,@Arg(desc="The list ID to select") int index) throws WorldEditException {
  LocalConfiguration config=we.getConfiguration();
  if (config.snapshotRepo == null) {
    player.printError("Snapshot/backup restore is not configured.");
    return;
  }
  if (index < 1) {
    player.printError("Invalid index, must be equal or higher then 1.");
    return;
  }
  try {
    List<Snapshot> snapshots=config.snapshotRepo.getSnapshots(true,player.getWorld().getName());
    if (snapshots.size() < index) {
      player.printError("Invalid index, must be between 1 and " + snapshots.size() + ".");
      return;
    }
    Snapshot snapshot=snapshots.get(index - 1);
    if (snapshot == null) {
      player.printError("That snapshot does not exist or is not available.");
      return;
    }
    session.setSnapshot(snapshot);
    player.print("Snapshot set to: " + snapshot.getName());
  }
 catch (  MissingWorldException e) {
    player.printError("No snapshots were found for this world.");
  }
}
