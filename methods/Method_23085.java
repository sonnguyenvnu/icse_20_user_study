/** 
 * This method is called after data has been exported.  This method should remove the data that was transfered if the action was MOVE.
 * @param comp The component that was the source of the data.
 * @param data   The data that was transferred or possibly nullif the action is <code>NONE</code>.
 * @param action The actual action that was performed.
 */
@Override protected void exportDone(JComponent comp,Transferable data,int action){
  JTextComponent c=(JTextComponent)comp;
  if (shouldRemove && action == MOVE) {
    try {
      Document doc=c.getDocument();
      doc.remove(p0,p1 - p0);
    }
 catch (    BadLocationException e) {
    }
  }
  exportComp=null;
}
