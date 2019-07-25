/** 
 * Decode the data.
 * @param freq the frequency table (will be scaled)
 * @param data the compressed data
 * @param length the target length
 * @return the uncompressed result
 */
public static byte[] decode(int[] freq,byte[] data,int length){
  scaleFrequencies(freq,1 << SHIFT);
  int[] cumulativeFreq=generateCumulativeFrequencies(freq);
  byte[] freqToCode=generateFrequencyToCode(cumulativeFreq);
  byte[] out=new byte[length];
  decode(data,freq,cumulativeFreq,freqToCode,out);
  return out;
}
