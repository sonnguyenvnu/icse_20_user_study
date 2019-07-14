public static @ImageOrigin int mapProducerNameToImageOrigin(final String producerName){
switch (producerName) {
case BitmapMemoryCacheGetProducer.PRODUCER_NAME:
case BitmapMemoryCacheProducer.PRODUCER_NAME:
case PostprocessedBitmapMemoryCacheProducer.PRODUCER_NAME:
    return ImageOrigin.MEMORY_BITMAP;
case EncodedMemoryCacheProducer.PRODUCER_NAME:
  return ImageOrigin.MEMORY_ENCODED;
case DiskCacheReadProducer.PRODUCER_NAME:
case PartialDiskCacheProducer.PRODUCER_NAME:
return ImageOrigin.DISK;
case NetworkFetchProducer.PRODUCER_NAME:
return ImageOrigin.NETWORK;
case DataFetchProducer.PRODUCER_NAME:
case LocalAssetFetchProducer.PRODUCER_NAME:
case LocalContentUriFetchProducer.PRODUCER_NAME:
case LocalContentUriThumbnailFetchProducer.PRODUCER_NAME:
case LocalFileFetchProducer.PRODUCER_NAME:
case LocalResourceFetchProducer.PRODUCER_NAME:
case LocalVideoThumbnailProducer.PRODUCER_NAME:
case QualifiedResourceFetchProducer.PRODUCER_NAME:
return ImageOrigin.LOCAL;
default :
return ImageOrigin.UNKNOWN;
}
}
