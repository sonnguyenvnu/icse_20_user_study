private void execute(boolean replaceAllDuplicates){
  myRefactoring.setReplacingAll(replaceAllDuplicates);
  myRefactoring.setIsFinal(false);
  myRefactoring.setName(ListSequence.fromList(myRefactoring.getExpectedNames()).first());
  EditorComputable<SNode> command=new EditorComputable<SNode>(myEditorContext){
    @Override protected SNode doCompute(){
      SNode result=myRefactoring.doRefactoring();
      MoveRefactoringUtils.fixImportsFromNode(result);
      return result;
    }
  }
;
  myEditorContext.getRepository().getModelAccess().executeCommand(command);
  EditorCell cell=CellFinder.getCellForProperty(myEditorComponent,command.getResult(),"name");
  if (cell == null) {
    myEditorContext.select(command.getResult());
  }
 else {
    if (cell instanceof EditorCell_Label) {
      EditorCell_Label ecl=((EditorCell_Label)cell);
      myEditorComponent.getSelectionManager().setSelection(ecl,0,0,ecl.getText().length());
    }
 else {
      myEditorComponent.getSelectionManager().setSelection(cell);
    }
  }
}
