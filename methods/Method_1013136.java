public final int read(){
  if (fOffset == fBufferLength) {
    int end=fBufferOffset + fBufferLength;
    if (end == fDocument.getLength() || end == fRangeOffset + fRangeLength)     return EOF;
 else {
      updateBuffer(fBufferOffset + fBufferLength);
      fOffset=0;
    }
  }
  try {
    return fBuffer[fOffset++];
  }
 catch (  ArrayIndexOutOfBoundsException ex) {
    StringBuffer buf=new StringBuffer();
    buf.append("Detailed state of 'BufferedDocumentScanner:'");
    buf.append("\n\tfOffset= ");
    buf.append(fOffset);
    buf.append("\n\tfBufferOffset= ");
    buf.append(fBufferOffset);
    buf.append("\n\tfBufferLength= ");
    buf.append(fBufferLength);
    buf.append("\n\tfRangeOffset= ");
    buf.append(fRangeOffset);
    buf.append("\n\tfRangeLength= ");
    buf.append(fRangeLength);
    TLAEditorActivator.getDefault().getLog().log(new Status(IStatus.WARNING,TLAEditorActivator.PLUGIN_ID,buf.toString(),ex));
    throw ex;
  }
}
