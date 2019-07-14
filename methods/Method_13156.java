@Override public void index(API api,Container.Entry entry,Indexes indexes){
  for (  Container.Entry e : entry.getChildren()) {
    if (e.isDirectory() && e.getPath().equals("classes")) {
      Map<String,Collection> packageDeclarationIndex=indexes.getIndex("packageDeclarations");
      index(api,e,indexes,packageDeclarationIndex);
      break;
    }
  }
}
