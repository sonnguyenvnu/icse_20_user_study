/** 
 * Provide the implementation of the PlatformBitmapFactory for the current platform using the provided PoolFactory
 * @param poolFactory The PoolFactory
 * @param platformDecoder The PlatformDecoder
 * @return The PlatformBitmapFactory implementation
 */
public static PlatformBitmapFactory buildPlatformBitmapFactory(PoolFactory poolFactory,PlatformDecoder platformDecoder,CloseableReferenceFactory closeableReferenceFactory){
  if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
    return new ArtBitmapFactory(poolFactory.getBitmapPool(),closeableReferenceFactory);
  }
 else   if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
    return new HoneycombBitmapFactory(new EmptyJpegGenerator(poolFactory.getPooledByteBufferFactory()),platformDecoder,closeableReferenceFactory);
  }
 else {
    return new GingerbreadBitmapFactory();
  }
}
