@Command(name="/hcyl",desc="Generates a hollow cylinder.") @CommandPermissions("worldedit.generation.cylinder") @Logging(PLACEMENT) public int hcyl(Player player,LocalSession session,EditSession editSession,@Arg(desc="The pattern of blocks to generate") Pattern pattern,@Arg(desc="The radii of the cylinder. 1st is N/S, 2nd is E/W") @Radii(2) List<Double> radii,@Arg(desc="The height of the cylinder",def="1") int height) throws WorldEditException {
  return cyl(player,session,editSession,pattern,radii,height,true);
}
