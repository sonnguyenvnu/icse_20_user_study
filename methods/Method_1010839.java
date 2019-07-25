@Override public Editor create(@NotNull Context context){
  return new IdeaNodeEditor(myProject,context.getNode());
}
