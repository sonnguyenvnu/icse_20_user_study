@Override public void apply(@NotNull SModel model,@NotNull NodeCopier nodeCopier){
  if (isDelete()) {
    myType.myDeleteTask.invoke(model,myModuleReference);
  }
 else {
    myType.myAddTask.invoke(model,myModuleReference);
  }
}
