private void buildFlatTreeFromMass(List<String> mass){
  TreeNodeUserObject topNodeUserObject=new TreeNodeUserObject(getName(file.getName()));
  DefaultMutableTreeNode top=new DefaultMutableTreeNode(topNodeUserObject);
  TreeMap<String,TreeSet<String>> packages=new TreeMap<>();
  HashSet<String> classContainingPackageRoots=new HashSet<>();
  Comparator<String> sortByFileExtensionsComparator=new Comparator<String>(){
    @Override public int compare(    String o1,    String o2){
      int comp=o1.replaceAll("[^\\.]*\\.","").compareTo(o2.replaceAll("[^\\.]*\\.",""));
      if (comp != 0)       return comp;
      return o1.compareTo(o2);
    }
  }
;
  for (  String entry : mass) {
    String packagePath="";
    String packageRoot="";
    if (entry.contains("/")) {
      packagePath=entry.replaceAll("/[^/]*$","");
      packageRoot=entry.replaceAll("/.*$","");
    }
    String packageEntry=entry.replace(packagePath + "/","");
    if (!packages.containsKey(packagePath)) {
      packages.put(packagePath,new TreeSet<String>(sortByFileExtensionsComparator));
    }
    packages.get(packagePath).add(packageEntry);
    if (!entry.startsWith("META-INF") && packageRoot.trim().length() > 0 && entry.matches(".*\\.(class|java|prop|properties)$")) {
      classContainingPackageRoots.add(packageRoot);
    }
  }
  for (  String packagePath : packages.keySet()) {
    if (packagePath.startsWith("META-INF")) {
      List<String> packagePathElements=Arrays.asList(packagePath.split("/"));
      for (      String entry : packages.get(packagePath)) {
        ArrayList<String> list=new ArrayList<>(packagePathElements);
        list.add(entry);
        loadNodesByNames(top,list);
      }
    }
  }
  for (  String packagePath : packages.keySet()) {
    String packageRoot=packagePath.replaceAll("/.*$","");
    if (classContainingPackageRoots.contains(packageRoot)) {
      for (      String entry : packages.get(packagePath)) {
        ArrayList<TreeNodeUserObject> list=new ArrayList<>();
        list.add(new TreeNodeUserObject(packagePath,packagePath.replaceAll("/",".")));
        list.add(new TreeNodeUserObject(entry));
        loadNodesByUserObj(top,list);
      }
    }
  }
  for (  String packagePath : packages.keySet()) {
    String packageRoot=packagePath.replaceAll("/.*$","");
    if (!classContainingPackageRoots.contains(packageRoot) && !packagePath.startsWith("META-INF") && packagePath.length() > 0) {
      List<String> packagePathElements=Arrays.asList(packagePath.split("/"));
      for (      String entry : packages.get(packagePath)) {
        ArrayList<String> list=new ArrayList<>(packagePathElements);
        list.add(entry);
        loadNodesByNames(top,list);
      }
    }
  }
  String packagePath="";
  if (packages.containsKey(packagePath)) {
    for (    String entry : packages.get(packagePath)) {
      ArrayList<String> list=new ArrayList<>();
      list.add(entry);
      loadNodesByNames(top,list);
    }
  }
  tree.setModel(new DefaultTreeModel(top));
}
