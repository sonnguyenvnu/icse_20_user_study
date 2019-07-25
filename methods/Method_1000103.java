/** 
 * Push the block in the KhoasDB.
 */
public BlockCapsule push(BlockCapsule blk) throws UnLinkedBlockException, BadNumberBlockException {
  KhaosBlock block=new KhaosBlock(blk);
  if (head != null && block.getParentHash() != Sha256Hash.ZERO_HASH) {
    KhaosBlock kblock=miniStore.getByHash(block.getParentHash());
    if (kblock != null) {
      if (blk.getNum() != kblock.num + 1) {
        throw new BadNumberBlockException("parent number :" + kblock.num + ",block number :" + blk.getNum());
      }
      block.setParent(kblock);
    }
 else {
      miniUnlinkedStore.insert(block);
      throw new UnLinkedBlockException();
    }
  }
  miniStore.insert(block);
  if (head == null || block.num > head.num) {
    head=block;
  }
  return head.blk;
}
