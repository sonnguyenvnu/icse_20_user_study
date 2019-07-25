public Set<ClassVertex> build(Vertex<ClassNode> root){
  find(root,dummy());
  return visited().stream().map(v -> (ClassVertex)v).collect(Collectors.toSet());
}
