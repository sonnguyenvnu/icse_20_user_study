public int[] exclusiveTime(int n,List<String> logs){
  Stack<Integer> stack=new Stack<Integer>();
  int[] result=new int[n];
  String[] current=logs.get(0).split(":");
  stack.push(Integer.parseInt(current[0]));
  int i=1;
  int previous=Integer.parseInt(current[2]);
  while (i < logs.size()) {
    current=logs.get(i).split(":");
    if (current[1].equals("start")) {
      if (!stack.isEmpty()) {
        result[stack.peek()]+=Integer.parseInt(current[2]) - previous;
      }
      stack.push(Integer.parseInt(current[0]));
      previous=Integer.parseInt(current[2]);
    }
 else {
      result[stack.peek()]+=Integer.parseInt(current[2]) - previous + 1;
      stack.pop();
      previous=Integer.parseInt(current[2]) + 1;
    }
    i++;
  }
  return result;
}
