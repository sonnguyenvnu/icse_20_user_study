/** 
 * Notify this  {@link LineID} that it is no longer in use. Will stopposition tracking. Call this when this  {@link LineID} is no longerneeded.
 */
public synchronized void stopTracking(){
  if (doc != null) {
    doc.removeDocumentListener(this);
    doc=null;
  }
}
