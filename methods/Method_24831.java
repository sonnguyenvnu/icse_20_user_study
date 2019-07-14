public SketchInterval mapJavaToSketch(ASTNode node){
  return mapJavaToSketch(node.getStartPosition(),node.getStartPosition() + node.getLength());
}
