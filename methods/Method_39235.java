/** 
 * @param fileName String representing file name.
 * @return this
 * @see #content(File)
 */
public EmailAttachmentBuilder content(final String fileName){
  return content(new File(fileName));
}
