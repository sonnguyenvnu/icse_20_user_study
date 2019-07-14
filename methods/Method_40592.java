List<Diagnostic> getFileErrs(String file,@NotNull Map<String,List<Diagnostic>> map){
  List<Diagnostic> msgs=map.get(file);
  if (msgs == null) {
    msgs=new ArrayList<>();
    map.put(file,msgs);
  }
  return msgs;
}
