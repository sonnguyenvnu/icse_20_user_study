/** 
 * ??????????
 * @param node
 */
public void reverseNode1(Node node){
  System.out.println("====????====");
  Stack<Node> stack=new Stack<>();
  while (node != null) {
    System.out.print(node.value + "===>");
    stack.push(node);
    node=node.next;
  }
  System.out.println("");
  System.out.println("====????====");
  while (!stack.isEmpty()) {
    System.out.print(stack.pop().value + "===>");
  }
}
