/** 
 * Return the uncompressed bytes. 
 */
public byte[] uncompressed(){
  try {
    return BytesReference.toBytes(CompressorFactory.uncompress(new BytesArray(bytes)));
  }
 catch (  IOException e) {
    throw new IllegalStateException("Cannot decompress compressed string",e);
  }
}
