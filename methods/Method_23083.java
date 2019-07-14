@Override public boolean canImport(JComponent comp,DataFlavor[] transferFlavors){
  JTextComponent c=(JTextComponent)comp;
  if (!(c.isEditable() && c.isEnabled())) {
    return false;
  }
  for (  DataFlavor flavor : transferFlavors) {
    if (flavor.isFlavorJavaFileListType() || flavor.isFlavorTextType()) {
      return true;
    }
  }
  return false;
}
