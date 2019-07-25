@Command(name="list",aliases={"all","ls"},desc="List saved schematics",descFooter="Note: Format is not fully verified until loading.") @CommandPermissions("worldedit.schematic.list") public void list(Actor actor,@ArgFlag(name='p',desc="Page to view.",def="1") int page,@Switch(name='d',desc="Sort by date, oldest first") boolean oldFirst,@Switch(name='n',desc="Sort by date, newest first") boolean newFirst){
  if (oldFirst && newFirst) {
    throw new StopExecutionException(TextComponent.of("Cannot sort by oldest and newest."));
  }
  final String saveDir=worldEdit.getConfiguration().saveDir;
  final int sortType=oldFirst ? -1 : newFirst ? 1 : 0;
  final String pageCommand=actor.isPlayer() ? "//schem list -p %page%" + (sortType == -1 ? " -d" : sortType == 1 ? " -n" : "") : null;
  WorldEditAsyncCommandBuilder.createAndSendMessage(actor,new SchematicListTask(saveDir,sortType,page,pageCommand),"(Please wait... gathering schematic list.)");
}
