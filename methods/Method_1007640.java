private void recurse(EditSession editSession,BlockVector3 pos,BlockVector3 origin,int size,BlockType initialType,Set<BlockVector3> visited) throws MaxChangedBlocksException {
  if (origin.distance(pos) > size || visited.contains(pos)) {
    return;
  }
  visited.add(pos);
  if (editSession.getBlock(pos).getBlockType() == initialType) {
    editSession.setBlock(pos,pattern.apply(pos));
  }
 else {
    return;
  }
  recurse(editSession,pos.add(1,0,0),origin,size,initialType,visited);
  recurse(editSession,pos.add(-1,0,0),origin,size,initialType,visited);
  recurse(editSession,pos.add(0,0,1),origin,size,initialType,visited);
  recurse(editSession,pos.add(0,0,-1),origin,size,initialType,visited);
  recurse(editSession,pos.add(0,1,0),origin,size,initialType,visited);
  recurse(editSession,pos.add(0,-1,0),origin,size,initialType,visited);
}
