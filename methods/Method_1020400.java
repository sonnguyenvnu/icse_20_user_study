/** 
 * No-op implementation of the  {@link Span#end(EndSpanOptions)} method. 
 */
@Override public void end(EndSpanOptions options){
  Utils.checkNotNull(options,"options");
}
