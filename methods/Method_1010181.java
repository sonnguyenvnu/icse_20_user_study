@SuppressWarnings("unused") public void dump(){
  for (  DeltaRoot dr : myDelta) {
    if (dr instanceof NewRoot) {
      System.out.printf("+%s\n",SNodeOperations.getDebugText(((NewRoot)dr).myRoot));
    }
 else     if (dr instanceof ReplacedRoot) {
      ReplacedRoot rr=(ReplacedRoot)dr;
      System.out.printf("R%s - %d\n",SNodeOperations.getDebugText(rr.myReplacedRoot),rr.myReplacements.size());
    }
 else     if (dr instanceof DeletedRoot) {
      System.out.printf("-%s\n",SNodeOperations.getDebugText(((DeletedRoot)dr).myRoot));
    }
 else {
      CopyRoot root=(CopyRoot)dr;
      char c=root.mySubTrees.length > 0 ? '*' : '~';
      System.out.printf("%c%s\n",c,SNodeOperations.getDebugText(root.myRoot));
      for (      SubTree tree : root.mySubTrees) {
        if (tree.isSourceCopy()) {
          System.out.printf("    copysrc %s\n",tree.myInputNode);
        }
 else {
          StringBuilder sb=new StringBuilder();
          for (          SNode r : tree.myReplacement) {
            sb.append(r.toString());
            sb.append(',');
          }
          System.out.printf("    %s - %d - %s -> (%s)\n",tree.myRoleInParent,tree.myReplacement.size(),tree.myInputNode,sb);
        }
      }
    }
  }
  System.out.println();
}
