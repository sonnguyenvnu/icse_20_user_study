public void dispose(){
  myActionGroup.removeAll();
  myActionGroup=null;
  myOldEditor.dispose();
  myOldEditor=null;
  myNewEditor.dispose();
  myNewEditor=null;
  ListSequence.fromList(myEditorSeparators).visitAll(new IVisitor<DiffEditorSeparator>(){
    public void visit(    DiffEditorSeparator s){
      s.dispose();
    }
  }
);
  ListSequence.fromList(myEditorSeparators).clear();
  myEditorSeparators=null;
}
