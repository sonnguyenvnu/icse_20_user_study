/** 
 * Add opcode to instructions.
 * @param location Anchor node.
 * @param created Node to add.
 * @param mode Direction from anchor to insert node at.
 */
private void add(AbstractInsnNode location,AbstractInsnNode created,InsertMode mode){
  Threads.runFx(() -> {
    ObservableList<AbstractInsnNode> obsList=list.getInsnList().getItems();
    int index=obsList.indexOf(location);
    if (mode == InsertMode.BEFORE) {
      obsList.add(index,created);
    }
 else {
      obsList.add(index + 1,created);
    }
    Stage stage=(Stage)getScene().getWindow();
    stage.close();
  }
);
}
