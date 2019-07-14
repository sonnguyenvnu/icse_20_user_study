/** 
 * Returns whether the sample at the given index has a subsample encryption table. 
 */
public boolean sampleHasSubsampleEncryptionTable(int index){
  return definesEncryptionData && sampleHasSubsampleEncryptionTable[index];
}
