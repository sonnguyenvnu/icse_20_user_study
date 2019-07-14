/** 
 * @see ViewerModelListener#viewerModelChanged(ViewerModelEvent)
 */
@Override @SuppressWarnings("PMD.UseArrayListInsteadOfVector") public void viewerModelChanged(ViewerModelEvent e){
switch (e.getReason()) {
case ViewerModelEvent.PATH_EXPRESSION_EVALUATED:
    if (e.getSource() != this) {
      list.setListData(new Vector(model.getLastEvaluationResults()));
    }
  break;
case ViewerModelEvent.CODE_RECOMPILED:
list.setListData(new Vector(0));
break;
default :
break;
}
}
