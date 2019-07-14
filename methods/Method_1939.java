/** 
 * Configures disk and memory cache not to exceed common limits
 */
private static void configureCaches(ImagePipelineConfig.Builder configBuilder,Context context){
  final MemoryCacheParams bitmapCacheParams=new MemoryCacheParams(ConfigConstants.MAX_MEMORY_CACHE_SIZE,Integer.MAX_VALUE,ConfigConstants.MAX_MEMORY_CACHE_SIZE,Integer.MAX_VALUE,Integer.MAX_VALUE,TimeUnit.MINUTES.toMillis(5));
  configBuilder.setBitmapMemoryCacheParamsSupplier(new Supplier<MemoryCacheParams>(){
    public MemoryCacheParams get(){
      return bitmapCacheParams;
    }
  }
).setMainDiskCacheConfig(DiskCacheConfig.newBuilder(context).setBaseDirectoryPath(context.getApplicationContext().getCacheDir()).setBaseDirectoryName(IMAGE_PIPELINE_CACHE_DIR).setMaxCacheSize(ConfigConstants.MAX_DISK_CACHE_SIZE).build());
}
