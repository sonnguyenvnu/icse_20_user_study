/** 
 * Initializes  {@link ImagePipelineFactory} with the specified config. 
 */
public static synchronized void initialize(ImagePipelineConfig imagePipelineConfig){
  if (sInstance != null) {
    FLog.w(TAG,"ImagePipelineFactory has already been initialized! `ImagePipelineFactory.initialize(...)` should only be called once to avoid unexpected behavior.");
  }
  sInstance=new ImagePipelineFactory(imagePipelineConfig);
}
