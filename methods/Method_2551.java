void expandUnits(){
  int srcNumUnits=_units.size();
  int srcNumBlocks=numBlocks();
  int destNumUnits=srcNumUnits + BLOCK_SIZE;
  int destNumBlocks=srcNumBlocks + 1;
  if (destNumBlocks > NUM_EXTRA_BLOCKS) {
    fixBlock(srcNumBlocks - NUM_EXTRA_BLOCKS);
  }
  _units.resize(destNumUnits);
  if (destNumBlocks > NUM_EXTRA_BLOCKS) {
    for (int id=srcNumUnits; id < destNumUnits; ++id) {
      extras(id).isUsed=false;
      extras(id).isFixed=false;
    }
  }
  for (int i=srcNumUnits + 1; i < destNumUnits; ++i) {
    extras(i - 1).next=i;
    extras(i).prev=i - 1;
  }
  extras(srcNumUnits).prev=destNumUnits - 1;
  extras(destNumUnits - 1).next=srcNumUnits;
  extras(srcNumUnits).prev=extras(_extrasHead).prev;
  extras(destNumUnits - 1).next=_extrasHead;
  extras(extras(_extrasHead).prev).next=srcNumUnits;
  extras(_extrasHead).prev=destNumUnits - 1;
}
