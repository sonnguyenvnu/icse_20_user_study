/** 
 * ??????????
 * @param parent   ???
 * @param siblings ???????
 * @return ??????
 */
private int fetch(Node parent,List<Node> siblings){
  if (error_ < 0)   return 0;
  int prev=0;
  for (int i=parent.left; i < parent.right; i++) {
    if ((length != null ? length[i] : key.get(i).length()) < parent.depth)     continue;
    String tmp=key.get(i);
    int cur=0;
    if ((length != null ? length[i] : tmp.length()) != parent.depth)     cur=(int)tmp.charAt(parent.depth) + 1;
    if (prev > cur) {
      error_=-3;
      return 0;
    }
    if (cur != prev || siblings.size() == 0) {
      Node tmp_node=new Node();
      tmp_node.depth=parent.depth + 1;
      tmp_node.code=cur;
      tmp_node.left=i;
      if (siblings.size() != 0)       siblings.get(siblings.size() - 1).right=i;
      siblings.add(tmp_node);
    }
    prev=cur;
  }
  if (siblings.size() != 0)   siblings.get(siblings.size() - 1).right=parent.right;
  return siblings.size();
}
