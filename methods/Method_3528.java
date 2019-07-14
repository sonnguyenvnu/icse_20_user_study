/** 
 * ???????????????????????
 * @return
 */
public Integer[] getBestPath(){
  assert (vertexCount > 2);
  Stack<Integer> stack=new Stack<Integer>();
  int curNode=vertexCount - 1, curIndex=0;
  QueueElement element;
  element=fromArray[curNode - 1][curIndex].GetFirst();
  stack.push(curNode);
  stack.push(element.from);
  curNode=element.from;
  while (curNode != 0) {
    element=fromArray[element.from - 1][element.index].GetFirst();
    stack.push(element.from);
    curNode=element.from;
  }
  return (Integer[])stack.toArray();
}
