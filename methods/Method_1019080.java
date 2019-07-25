public void refresh(AbstractInsnNode insn){
  Threads.runFx(() -> {
    getItems().clear();
    setupItems(insn);
    if (insn.getType() == AbstractInsnNode.LDC_INSN) {
      addTypeSwitcher(insn);
    }
    addOpcodeSwitcher(insn);
  }
);
}
