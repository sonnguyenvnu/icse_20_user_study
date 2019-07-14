private static void makeTextComponentUndoable(JTextComponent textConponent){
  final UndoManager undoManager=new UndoManager();
  textConponent.getDocument().addUndoableEditListener(new UndoableEditListener(){
    @Override public void undoableEditHappened(    UndoableEditEvent evt){
      undoManager.addEdit(evt.getEdit());
    }
  }
);
  ActionMap actionMap=textConponent.getActionMap();
  InputMap inputMap=textConponent.getInputMap();
  actionMap.put("Undo",new AbstractAction("Undo"){
    @Override public void actionPerformed(    ActionEvent evt){
      try {
        if (undoManager.canUndo()) {
          undoManager.undo();
        }
      }
 catch (      CannotUndoException e) {
        throw new RuntimeException(e);
      }
    }
  }
);
  inputMap.put(KeyStroke.getKeyStroke("control Z"),"Undo");
  actionMap.put("Redo",new AbstractAction("Redo"){
    @Override public void actionPerformed(    ActionEvent evt){
      try {
        if (undoManager.canRedo()) {
          undoManager.redo();
        }
      }
 catch (      CannotRedoException e) {
        throw new RuntimeException(e);
      }
    }
  }
);
  inputMap.put(KeyStroke.getKeyStroke("control Y"),"Redo");
}
