@Command(name="/hsphere",desc="Generates a hollow sphere.") @CommandPermissions("worldedit.generation.sphere") @Logging(PLACEMENT) public int hsphere(Player player,LocalSession session,EditSession editSession,@Arg(desc="The pattern of blocks to generate") Pattern pattern,@Arg(desc="The radii of the sphere. Order is N/S, U/D, E/W") @Radii(3) List<Double> radii,@Switch(name='r',desc="Raise the bottom of the sphere to the placement position") boolean raised) throws WorldEditException {
  return sphere(player,session,editSession,pattern,radii,raised,true);
}
