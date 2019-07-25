@Command(name="/cyl",desc="Generates a cylinder.") @CommandPermissions("worldedit.generation.cylinder") @Logging(PLACEMENT) public int cyl(Player player,LocalSession session,EditSession editSession,@Arg(desc="The pattern of blocks to generate") Pattern pattern,@Arg(desc="The radii of the cylinder. 1st is N/S, 2nd is E/W") @Radii(2) List<Double> radii,@Arg(desc="The height of the cylinder",def="1") int height,@Switch(name='h',desc="Make a hollow cylinder") boolean hollow) throws WorldEditException {
  final double radiusX, radiusZ;
switch (radii.size()) {
case 1:
    radiusX=radiusZ=Math.max(1,radii.get(0));
  break;
case 2:
radiusX=Math.max(1,radii.get(0));
radiusZ=Math.max(1,radii.get(1));
break;
default :
player.printError("You must either specify 1 or 2 radius values.");
return 0;
}
worldEdit.checkMaxRadius(radiusX);
worldEdit.checkMaxRadius(radiusZ);
worldEdit.checkMaxRadius(height);
BlockVector3 pos=session.getPlacementPosition(player);
int affected=editSession.makeCylinder(pos,pattern,radiusX,radiusZ,height,!hollow);
player.print(affected + " block(s) have been created.");
return affected;
}
