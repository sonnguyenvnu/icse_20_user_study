private void addSemanticStyle(@NotNull Binding binding,Style.Type type){
  String path=binding.getFile();
  if (path != null) {
    addFileStyle(path,new Style(type,binding.start,binding.end));
  }
}
