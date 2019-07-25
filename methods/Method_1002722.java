/** 
 * A dummy  {@link ContentPreviewerFactory} which returns {@link ContentPreviewer#disabled()}.
 */
static ContentPreviewerFactory disabled(){
  return NoopContentPreviewerFactory.INSTANCE;
}
