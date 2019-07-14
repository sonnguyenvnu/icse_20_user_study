private static boolean needsBlock(TreePath path){
  Tree leaf=path.getLeaf();
class Visitor extends SimpleTreeVisitor<Boolean,Void> {
    @Override public Boolean visitIf(    IfTree tree,    Void unused){
      return tree.getThenStatement() == leaf || tree.getElseStatement() == leaf;
    }
    @Override public Boolean visitDoWhileLoop(    DoWhileLoopTree tree,    Void unused){
      return tree.getStatement() == leaf;
    }
    @Override public Boolean visitWhileLoop(    WhileLoopTree tree,    Void unused){
      return tree.getStatement() == leaf;
    }
    @Override public Boolean visitForLoop(    ForLoopTree tree,    Void unused){
      return tree.getStatement() == leaf;
    }
    @Override public Boolean visitEnhancedForLoop(    EnhancedForLoopTree tree,    Void unused){
      return tree.getStatement() == leaf;
    }
  }
  return firstNonNull(path.getParentPath().getLeaf().accept(new Visitor(),null),false);
}
