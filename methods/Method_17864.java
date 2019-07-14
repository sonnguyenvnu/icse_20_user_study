/** 
 * @return the text content that is mounted on this host.
 */
@DoNotStrip public TextContent getTextContent(){
  ensureMountItems();
  return ComponentHostUtils.extractTextContent(ComponentHostUtils.extractContent(mMountItems));
}
