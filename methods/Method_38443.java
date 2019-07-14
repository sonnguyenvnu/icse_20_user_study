/** 
 * Invoked before resource content is stored in the bundle. May be us used for additional resource processing, such as compressing, cleaning etc. By default it just returns unmodified content.
 */
protected String onResourceContent(final String content){
  return content;
}
