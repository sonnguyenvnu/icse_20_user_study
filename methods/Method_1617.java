/** 
 * Provide the implementation of the PlatformDecoder for the current platform using the provided PoolFactory
 * @param poolFactory The PoolFactory
 * @return The PlatformDecoder implementation
 */
public static PlatformDecoder buildPlatformDecoder(PoolFactory poolFactory,boolean gingerbreadDecoderEnabled){
  if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
    int maxNumThreads=poolFactory.getFlexByteArrayPoolMaxNumThreads();
    return new OreoDecoder(poolFactory.getBitmapPool(),maxNumThreads,new Pools.SynchronizedPool<>(maxNumThreads));
  }
 else   if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
    int maxNumThreads=poolFactory.getFlexByteArrayPoolMaxNumThreads();
    return new ArtDecoder(poolFactory.getBitmapPool(),maxNumThreads,new Pools.SynchronizedPool<>(maxNumThreads));
  }
 else {
    if (gingerbreadDecoderEnabled && Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) {
      return new GingerbreadPurgeableDecoder();
    }
 else {
      return new KitKatPurgeableDecoder(poolFactory.getFlexByteArrayPool());
    }
  }
}
