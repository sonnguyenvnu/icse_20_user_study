/** 
 * Returns a new instance of Fresco Drawee controller builder. 
 */
public static PipelineDraweeControllerBuilder newDraweeControllerBuilder(){
  return sDraweeControllerBuilderSupplier.get();
}
