@Override protected LabelButton create(ListView<LabelNode> view){
  MethodNode method=(MethodNode)item.getOwner();
  return new LabelButton(Lang.get("ui.bean.opcode.label.nullvalue"),method);
}
