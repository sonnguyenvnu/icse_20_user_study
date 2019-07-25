@Override public void scan(String base,Pattern pattern,List<NutResource> list){
  if (!base.startsWith("/"))   base="/" + base;
  List<String> paths=new ArrayList<String>();
  getResources("/WEB-INF/classes" + base,paths);
  for (  final String path : paths) {
    if (path.equals(base)) {
      list.add(new WebClassesResource(path,base));
      continue;
    }
    String name=path.substring(path.lastIndexOf('/'));
    if (pattern == null || pattern.matcher(name).find()) {
      list.add(new WebClassesResource(path,base));
    }
  }
}
