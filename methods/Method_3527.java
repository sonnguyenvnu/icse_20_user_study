/** 
 * ???index+1????
 * @param index index ? 0 : ?????? index = 1 ? ?????, ?????index <= this.N
 * @return
 */
public List<int[]> getPaths(int index){
  assert (index <= N && index >= 0);
  Stack<PathNode> stack=new Stack<PathNode>();
  int curNode=vertexCount - 1, curIndex=index;
  QueueElement element;
  PathNode node;
  int[] aPath;
  List<int[]> result=new ArrayList<int[]>();
  element=fromArray[curNode - 1][curIndex].GetFirst();
  while (element != null) {
    stack.push(new PathNode(curNode,curIndex));
    stack.push(new PathNode(element.from,element.index));
    curNode=element.from;
    while (curNode != 0) {
      element=fromArray[element.from - 1][element.index].GetFirst();
      stack.push(new PathNode(element.from,element.index));
      curNode=element.from;
    }
    PathNode[] nArray=new PathNode[stack.size()];
    for (int i=0; i < stack.size(); ++i) {
      nArray[i]=stack.get(stack.size() - i - 1);
    }
    aPath=new int[nArray.length];
    for (int i=0; i < aPath.length; i++)     aPath[i]=nArray[i].from;
    result.add(aPath);
    do {
      node=stack.pop();
      curNode=node.from;
      curIndex=node.index;
    }
 while (curNode < 1 || (stack.size() != 0 && !fromArray[curNode - 1][curIndex].CanGetNext()));
    element=fromArray[curNode - 1][curIndex].GetNext();
  }
  return result;
}
