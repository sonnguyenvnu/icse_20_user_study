@Command(name="cs",desc="Execute a CraftScript") @CommandPermissions("worldedit.scripting.execute") @Logging(ALL) public void execute(Player player,LocalSession session,@Arg(desc="Filename of the CraftScript to load") String filename,@Arg(desc="Arguments to the CraftScript",def="",variable=true) List<String> args) throws WorldEditException {
  if (!player.hasPermission("worldedit.scripting.execute." + filename)) {
    player.printError("You don't have permission to use that script.");
    return;
  }
  session.setLastScript(filename);
  File dir=worldEdit.getWorkingDirectoryFile(worldEdit.getConfiguration().scriptsDir);
  File f=worldEdit.getSafeOpenFile(player,dir,filename,"js","js");
  worldEdit.runScript(player,f,Stream.concat(Stream.of(filename),args.stream()).toArray(String[]::new));
}
