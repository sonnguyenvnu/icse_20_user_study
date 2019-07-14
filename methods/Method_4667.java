/** 
 * Configures the fragment for the specified number of samples. <p> The  {@link #sampleCount} of the fragment is set to the specified sample count, and thecontained tables are resized if necessary such that they are at least this length.
 * @param sampleCount The number of samples in the new run.
 */
public void initTables(int trunCount,int sampleCount){
  this.trunCount=trunCount;
  this.sampleCount=sampleCount;
  if (trunLength == null || trunLength.length < trunCount) {
    trunDataPosition=new long[trunCount];
    trunLength=new int[trunCount];
  }
  if (sampleSizeTable == null || sampleSizeTable.length < sampleCount) {
    int tableSize=(sampleCount * 125) / 100;
    sampleSizeTable=new int[tableSize];
    sampleCompositionTimeOffsetTable=new int[tableSize];
    sampleDecodingTimeTable=new long[tableSize];
    sampleIsSyncFrameTable=new boolean[tableSize];
    sampleHasSubsampleEncryptionTable=new boolean[tableSize];
  }
}
