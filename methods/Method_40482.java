List<Diagnostic> getFileErrs(String file,Map<String,List<Diagnostic>> map){
  List<Diagnostic> msgs=map.get(file);
  if (msgs == null) {
    msgs=new ArrayList<Diagnostic>();
    map.put(file,msgs);
  }
  return msgs;
}
