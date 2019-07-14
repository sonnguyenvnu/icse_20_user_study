/** 
 * Fills  {@link #sampleEncryptionData} from the provided input.
 * @param input An {@link ExtractorInput} from which to read the encryption data.
 */
public void fillEncryptionData(ExtractorInput input) throws IOException, InterruptedException {
  input.readFully(sampleEncryptionData.data,0,sampleEncryptionDataLength);
  sampleEncryptionData.setPosition(0);
  sampleEncryptionDataNeedsFill=false;
}
