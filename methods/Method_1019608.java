/** 
 * Loads the specified file in this editor.  This method fires a property change event of type  {@link #FULL_PATH_PROPERTY}.
 * @param loc The location of the file to load.  This cannot be<code>null</code>.
 * @param defaultEnc The encoding to use when loading/saving the file.This encoding will only be used if the file is not Unicode. If this value is <code>null</code>, the system default encoding is used.
 * @throws IOException If an IO error occurs.
 * @see #save()
 * @see #saveAs(FileLocation)
 */
public void load(FileLocation loc,String defaultEnc) throws IOException {
  if (loc.isLocal() && !loc.isLocalAndExists()) {
    this.charSet=defaultEnc != null ? defaultEnc : getDefaultEncoding();
    this.loc=loc;
    setText(null);
    discardAllEdits();
    setDirty(false);
    return;
  }
  UnicodeReader ur=new UnicodeReader(loc.getInputStream(),defaultEnc);
  Document doc=getDocument();
  doc.removeDocumentListener(this);
  BufferedReader r=new BufferedReader(ur);
  try {
    read(r,null);
  }
  finally {
    doc.addDocumentListener(this);
    r.close();
  }
  charSet=ur.getEncoding();
  String old=getFileFullPath();
  this.loc=loc;
  setDirty(false);
  setCaretPosition(0);
  discardAllEdits();
  firePropertyChange(FULL_PATH_PROPERTY,old,getFileFullPath());
}
