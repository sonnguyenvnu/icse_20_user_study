/** 
 * Fills  {@link #sampleEncryptionData} from the provided source.
 * @param source A source from which to read the encryption data.
 */
public void fillEncryptionData(ParsableByteArray source){
  source.readBytes(sampleEncryptionData.data,0,sampleEncryptionDataLength);
  sampleEncryptionData.setPosition(0);
  sampleEncryptionDataNeedsFill=false;
}
