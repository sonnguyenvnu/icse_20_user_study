/** 
 * {@link Instrumenter#instrumentLithoHandler(com.facebook.litho.LithoHandler)} 
 */
public static LithoHandler instrumentLithoHandler(LithoHandler lithoHandler){
  final Instrumenter instrumenter=sInstance;
  if (instrumenter == null) {
    return lithoHandler;
  }
  return instrumenter.instrumentLithoHandler(lithoHandler);
}
