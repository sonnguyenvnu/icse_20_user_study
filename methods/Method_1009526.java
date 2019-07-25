public static Tree solve(){
  Scanner scan=new Scanner(System.in);
  int numNodes=scan.nextInt();
  values=new int[numNodes];
  colors=new Color[numNodes];
  map=new HashMap<>(numNodes);
  for (int i=0; i < numNodes; i++) {
    values[i]=scan.nextInt();
  }
  for (int i=0; i < numNodes; i++) {
    colors[i]=scan.nextInt() == 0 ? Color.RED : Color.GREEN;
  }
  for (int i=0; i < numNodes - 1; i++) {
    int u=scan.nextInt();
    int v=scan.nextInt();
    HashSet<Integer> uNeighbors=map.get(u);
    if (uNeighbors == null) {
      uNeighbors=new HashSet<>();
      map.put(u,uNeighbors);
    }
    uNeighbors.add(v);
    HashSet<Integer> vNeighbors=map.get(v);
    if (vNeighbors == null) {
      vNeighbors=new HashSet<>();
      map.put(v,vNeighbors);
    }
    vNeighbors.add(u);
  }
  scan.close();
  if (numNodes == 1) {
    return new TreeLeaf(values[0],colors[0],0);
  }
  TreeNode root=new TreeNode(values[0],colors[0],0);
  addChildren(root,1);
  return root;
}
