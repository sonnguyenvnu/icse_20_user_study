/** 
 * Configures the fragment to be one that defines encryption data of the specified length. <p> {@link #definesEncryptionData} is set to true, {@link #sampleEncryptionDataLength} is set tothe specified length, and  {@link #sampleEncryptionData} is resized if necessary such that itis at least this length.
 * @param length The length in bytes of the encryption data.
 */
public void initEncryptionData(int length){
  if (sampleEncryptionData == null || sampleEncryptionData.limit() < length) {
    sampleEncryptionData=new ParsableByteArray(length);
  }
  sampleEncryptionDataLength=length;
  definesEncryptionData=true;
  sampleEncryptionDataNeedsFill=true;
}
