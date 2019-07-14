/** 
 * Decodes a byteArray containing jpeg encoded bytes into a purgeable bitmap <p>Adds a JFIF End-Of-Image marker if needed before decoding.
 * @param bytesRef the byte buffer that contains the encoded bytes
 * @param length the length of bytes for decox
 * @param options the options passed to the BitmapFactory
 * @return the decoded bitmap
 */
@Override protected Bitmap decodeJPEGByteArrayAsPurgeable(CloseableReference<PooledByteBuffer> bytesRef,int length,BitmapFactory.Options options){
  byte[] suffix=endsWithEOI(bytesRef,length) ? null : EOI;
  return decodeFileDescriptorAsPurgeable(bytesRef,length,suffix,options);
}
