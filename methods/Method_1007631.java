@Override public void build(EditSession editSession,BlockVector3 position,Pattern pattern,double size) throws MaxChangedBlocksException {
  CylinderRegion region=CylinderRegion.createRadius(editSession,position,size);
  List<? extends Entity> entities=editSession.getEntities(region);
  Operations.completeLegacy(new EntityVisitor(entities.iterator(),flags.createFunction()));
}
