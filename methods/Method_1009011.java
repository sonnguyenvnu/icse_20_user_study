/** 
 * fill out a List of SmallDocumentBlocks so that it fully occupies a set of big blocks
 * @param blocks the List to be filled out
 * @return number of big blocks the list encompasses
 */
public static int fill(POIFSBigBlockSize bigBlockSize,List<SmallDocumentBlock> blocks){
  int _blocks_per_big_block=getBlocksPerBigBlock(bigBlockSize);
  int count=blocks.size();
  int big_block_count=(count + _blocks_per_big_block - 1) / _blocks_per_big_block;
  int full_count=big_block_count * _blocks_per_big_block;
  for (; count < full_count; count++) {
    blocks.add(makeEmptySmallDocumentBlock(bigBlockSize));
  }
  return big_block_count;
}
