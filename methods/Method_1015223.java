public static Object slice(Object node,Object editor,int start,int end){
  if (node instanceof Object[]) {
    Object[] ary=new Object[end - start];
    arraycopy(node,start,ary,0,ary.length);
    return ary;
  }
 else {
    return ((Node)node).slice(start,end,editor);
  }
}
