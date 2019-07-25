@Command(name="formats",aliases={"listformats","f"},desc="List available formats") @CommandPermissions("worldedit.schematic.formats") public void formats(Actor actor){
  actor.print("Available clipboard formats (Name: Lookup names)");
  StringBuilder builder;
  boolean first=true;
  for (  ClipboardFormat format : ClipboardFormats.getAll()) {
    builder=new StringBuilder();
    builder.append(format.getName()).append(": ");
    for (    String lookupName : format.getAliases()) {
      if (!first) {
        builder.append(", ");
      }
      builder.append(lookupName);
      first=false;
    }
    first=true;
    actor.print(builder.toString());
  }
}
