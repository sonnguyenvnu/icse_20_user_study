@Override public void relayout(){
  EditorComponent editor=getEditorComponent();
  if (editor == null || editor.isDisposed()) {
    return;
  }
  Iterable<EditorCell> nonTrivialCells=Sequence.fromIterable(EditorUtils.getCellDescendants(editor.getRootCell())).where(new IWhereFilter<EditorCell>(){
    public boolean accept(    EditorCell cell){
      return !((cell instanceof EditorCell_Collection)) && cell.getWidth() * cell.getHeight() != 0;
    }
  }
);
  Set<Integer> yCoordinatesSet=SetSequence.fromSetWithValues(new HashSet<Integer>(),Sequence.fromIterable(nonTrivialCells).select(new ISelector<EditorCell,Integer>(){
    public Integer select(    EditorCell cell){
      return cell.getY();
    }
  }
));
  myPseudoLinesY=SetSequence.fromSet(yCoordinatesSet).sort(new ISelector<Integer,Integer>(){
    public Integer select(    Integer y){
      return y;
    }
  }
,true).toListSequence();
  myEditorLineRecords=ListSequence.fromList(new ArrayList<AnnotationColumn.LineRevisionRecord>());
  ListSequence.fromList(myPseudoLinesY).visitAll(new IVisitor<Integer>(){
    public void visit(    Integer t){
      ListSequence.fromList(myEditorLineRecords).addElement(null);
    }
  }
);
  editor.getEditorContext().getRepository().getModelAccess().runReadAction(new Runnable(){
    public void run(){
      for (int fileLine=0; fileLine < ListSequence.fromList(myFileLineToContent).count(); fileLine++) {
        LineContent lineContent=ListSequence.fromList(myFileLineToContent).getElement(fileLine);
        final VcsFileRevision fileLineRev=fileRevForLine(fileLine);
        if (fileLineRev == null) {
          continue;
        }
        for (        int pseudoLine : getPseudoLinesForContent(lineContent)) {
          final AnnotationColumn.LineRevisionRecord lr=ListSequence.fromList(myEditorLineRecords).getElement(pseudoLine);
          if (lr == null) {
            ListSequence.fromList(myEditorLineRecords).setElement(pseudoLine,new AnnotationColumn.LineRevisionRecord(lineContent.getNodeId(),fileLineRev,fileLine));
          }
 else {
            if (lr.nodeId.equals(lineContent.getNodeId())) {
              if (lr.rev.getRevisionDate().before(fileLineRev.getRevisionDate())) {
                lr.rev=fileLineRev;
                lr.fileLine=fileLine;
              }
            }
 else {
              if (!(lr.isAmongPrevious(lineContent.getNodeId()))) {
                ListSequence.fromList(myEditorLineRecords).setElement(pseudoLine,new AnnotationColumn.LineRevisionRecord(lineContent.getNodeId(),fileLineRev,fileLine,lr));
              }
            }
          }
        }
      }
    }
  }
);
  FontMetrics metrics=FontRegistry.getInstance().getFontMetrics(myFont);
  for (  AnnotationAspectSubcolumn aspectSubcolumn : ListSequence.fromList(myAspectSubcolumns)) {
    aspectSubcolumn.computeWidth(metrics,ListSequence.fromList(myEditorLineRecords).where(new NotNullWhereFilter<AnnotationColumn.LineRevisionRecord>()));
  }
  mySubcolumnInterval=metrics.stringWidth(" ");
  calculateCurrentPseudoLinesLater();
}
