@Command(name="gmask",aliases={"/gmask"},desc="Set the global mask") @CommandPermissions("worldedit.global-mask") public void gmask(Player player,LocalSession session,@Arg(desc="The mask to set",def="") Mask mask){
  if (mask == null) {
    session.setMask(null);
    player.print("Global mask disabled.");
  }
 else {
    session.setMask(mask);
    player.print("Global mask set.");
  }
}
