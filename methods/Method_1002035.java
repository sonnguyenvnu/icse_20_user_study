/** 
 * HKDF-Expand(PRK, info, L) -&gt; OKM
 * @param key a pseudorandom key of at least HashLen bytes (usually, the output from the extract step)
 * @param info context and application specific information (can be empty)
 * @param outputLength length of output keying material in bytes (&lt;= 255*HashLen)
 * @return output keying material
 */
public byte[] expand(SecretKey key,byte[] info,int outputLength){
  requireNonNull(key,"key must not be null");
  if (outputLength < 1) {
    throw new IllegalArgumentException("outputLength must be positive");
  }
  int hashLen=hash.getByteLength();
  if (outputLength > 255 * hashLen) {
    throw new IllegalArgumentException("outputLength must be less than or equal to 255*HashLen");
  }
  if (info == null) {
    info=new byte[0];
  }
  int n=(outputLength % hashLen == 0) ? outputLength / hashLen : (outputLength / hashLen) + 1;
  byte[] hashRound=new byte[0];
  ByteBuffer generatedBytes=ByteBuffer.allocate(Math.multiplyExact(n,hashLen));
  Mac mac=initMac(key);
  for (int roundNum=1; roundNum <= n; roundNum++) {
    mac.reset();
    ByteBuffer t=ByteBuffer.allocate(hashRound.length + info.length + 1);
    t.put(hashRound);
    t.put(info);
    t.put((byte)roundNum);
    hashRound=mac.doFinal(t.array());
    generatedBytes.put(hashRound);
  }
  byte[] result=new byte[outputLength];
  generatedBytes.rewind();
  generatedBytes.get(result,0,outputLength);
  return result;
}
