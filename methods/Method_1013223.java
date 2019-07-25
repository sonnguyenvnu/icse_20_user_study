public final TreeNode[] heirs(){
  if (zero == null && one == null) {
    return nullArray;
  }
 else {
    SyntaxTreeNode result[];
    if (zero != null) {
      if (one != null) {
        result=new SyntaxTreeNode[zero.length + one.length];
        System.arraycopy(zero,0,result,0,zero.length);
        System.arraycopy(one,0,result,zero.length,one.length);
      }
 else {
        result=new SyntaxTreeNode[zero.length];
        System.arraycopy(zero,0,result,0,zero.length);
      }
    }
 else {
      result=new SyntaxTreeNode[one.length];
      System.arraycopy(one,0,result,0,one.length);
    }
    return result;
  }
}
