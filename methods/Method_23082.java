@Override protected Transferable createTransferable(JComponent comp){
  CompositeTransferable t;
  JTextComponent c=(JTextComponent)comp;
  shouldRemove=true;
  p0=c.getSelectionStart();
  p1=c.getSelectionEnd();
  if (p0 != p1) {
    t=new CompositeTransferable();
    String text=c.getSelectedText();
    t.add(new StringTransferable(text));
    t.add(new PlainTextTransferable(text));
  }
 else {
    t=null;
  }
  return t;
}
