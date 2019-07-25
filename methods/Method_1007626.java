@Command(name="use",desc="Choose a snapshot to use") @CommandPermissions("worldedit.snapshots.restore") public void use(Player player,LocalSession session,@Arg(desc="Snapeshot to use") String name) throws WorldEditException {
  LocalConfiguration config=we.getConfiguration();
  if (config.snapshotRepo == null) {
    player.printError("Snapshot/backup restore is not configured.");
    return;
  }
  if (name.equalsIgnoreCase("latest")) {
    try {
      Snapshot snapshot=config.snapshotRepo.getDefaultSnapshot(player.getWorld().getName());
      if (snapshot != null) {
        session.setSnapshot(null);
        player.print("Now using newest snapshot.");
      }
 else {
        player.printError("No snapshots were found.");
      }
    }
 catch (    MissingWorldException ex) {
      player.printError("No snapshots were found for this world.");
    }
  }
 else {
    try {
      session.setSnapshot(config.snapshotRepo.getSnapshot(name));
      player.print("Snapshot set to: " + name);
    }
 catch (    InvalidSnapshotException e) {
      player.printError("That snapshot does not exist or is not available.");
    }
  }
}
