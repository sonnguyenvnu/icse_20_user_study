/** 
 * Build the operation.
 * @return the operation
 */
public Operation build(){
  BlockTransformExtent extent=new BlockTransformExtent(clipboard,transform);
  ForwardExtentCopy copy=new ForwardExtentCopy(extent,clipboard.getRegion(),clipboard.getOrigin(),targetExtent,to);
  copy.setTransform(transform);
  if (ignoreAirBlocks) {
    copy.setSourceMask(sourceMask == Masks.alwaysTrue() ? new ExistingBlockMask(clipboard) : new MaskIntersection(sourceMask,new ExistingBlockMask(clipboard)));
  }
 else {
    copy.setSourceMask(sourceMask);
  }
  copy.setCopyingEntities(copyEntities);
  copy.setCopyingBiomes(copyBiomes && clipboard.hasBiomes());
  return copy;
}
