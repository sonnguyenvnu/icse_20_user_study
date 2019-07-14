/** 
 * Populates the holder with data parsed from ID3  {@link Metadata}.
 * @param metadata The metadata from which to parse the gapless information.
 * @return Whether the holder was populated.
 */
public boolean setFromMetadata(Metadata metadata){
  for (int i=0; i < metadata.length(); i++) {
    Metadata.Entry entry=metadata.get(i);
    if (entry instanceof CommentFrame) {
      CommentFrame commentFrame=(CommentFrame)entry;
      if (GAPLESS_DESCRIPTION.equals(commentFrame.description) && setFromComment(commentFrame.text)) {
        return true;
      }
    }
 else     if (entry instanceof InternalFrame) {
      InternalFrame internalFrame=(InternalFrame)entry;
      if (GAPLESS_DOMAIN.equals(internalFrame.domain) && GAPLESS_DESCRIPTION.equals(internalFrame.description) && setFromComment(internalFrame.text)) {
        return true;
      }
    }
  }
  return false;
}
