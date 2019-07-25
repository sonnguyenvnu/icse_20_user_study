/** 
 * We override this method because the super version gives us an entirely new <code>Document</code>, thus requiring us to re-attach our Undo manager.  With this version we just replace the text.
 */
@Override public void read(Reader in,Object desc) throws IOException {
  RTextAreaEditorKit kit=(RTextAreaEditorKit)getUI().getEditorKit(this);
  setText(null);
  Document doc=getDocument();
  if (desc != null) {
    doc.putProperty(Document.StreamDescriptionProperty,desc);
  }
  try {
    kit.read(in,doc,0);
  }
 catch (  BadLocationException e) {
    throw new IOException(e.getMessage());
  }
}
