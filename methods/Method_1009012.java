/** 
 * create a list of SmallDocumentBlock's from raw data
 * @param blocks the raw data containing the SmallDocumentBlockdata
 * @return a List of SmallDocumentBlock's extracted from the input
 */
public static List<SmallDocumentBlock> extract(POIFSBigBlockSize bigBlockSize,ListManagedBlock[] blocks) throws IOException {
  int _blocks_per_big_block=getBlocksPerBigBlock(bigBlockSize);
  List<SmallDocumentBlock> sdbs=new ArrayList<SmallDocumentBlock>();
  for (int j=0; j < blocks.length; j++) {
    byte[] data=blocks[j].getData();
    for (int k=0; k < _blocks_per_big_block; k++) {
      sdbs.add(new SmallDocumentBlock(bigBlockSize,data,k));
    }
  }
  return sdbs;
}
