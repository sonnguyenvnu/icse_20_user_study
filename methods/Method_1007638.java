@Override public void build(EditSession editSession,BlockVector3 position,Pattern pattern,double size) throws MaxChangedBlocksException {
  if (pattern == null) {
    pattern=new BlockPattern(BlockTypes.COBBLESTONE.getDefaultState());
  }
  editSession.makeSphere(position,pattern,size,size,size,true);
}
