@Override public void build(EditSession editSession,BlockVector3 position,Pattern pattern,double size) throws MaxChangedBlocksException {
  EditContext context=new EditContext();
  context.setDestination(editSession);
  context.setRegion(regionFactory.createCenteredAt(position,size));
  context.setFill(pattern);
  context.setSession(session);
  Operation operation=operationFactory.createFromContext(context);
  Operations.completeLegacy(operation);
}
