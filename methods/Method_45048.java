private void buildDirectoryTreeFromMass(List<String> mass){
  TreeNodeUserObject topNodeUserObject=new TreeNodeUserObject(getName(file.getName()));
  DefaultMutableTreeNode top=new DefaultMutableTreeNode(topNodeUserObject);
  List<String> sort=new ArrayList<String>();
  Collections.sort(mass,String.CASE_INSENSITIVE_ORDER);
  for (  String m : mass)   if (m.contains("META-INF") && !sort.contains(m))   sort.add(m);
  Set<String> set=new HashSet<String>();
  for (  String m : mass) {
    if (m.contains("/")) {
      set.add(m.substring(0,m.lastIndexOf("/") + 1));
    }
  }
  List<String> packs=Arrays.asList(set.toArray(new String[]{}));
  Collections.sort(packs,String.CASE_INSENSITIVE_ORDER);
  Collections.sort(packs,new Comparator<String>(){
    public int compare(    String o1,    String o2){
      return o2.split("/").length - o1.split("/").length;
    }
  }
);
  for (  String pack : packs)   for (  String m : mass)   if (!m.contains("META-INF") && m.contains(pack) && !m.replace(pack,"").contains("/"))   sort.add(m);
  for (  String m : mass)   if (!m.contains("META-INF") && !m.contains("/") && !sort.contains(m))   sort.add(m);
  for (  String pack : sort) {
    LinkedList<String> list=new LinkedList<String>(Arrays.asList(pack.split("/")));
    loadNodesByNames(top,list);
  }
  tree.setModel(new DefaultTreeModel(top));
}
