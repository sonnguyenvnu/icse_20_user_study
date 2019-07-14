public int[] dailyTemperatures(int[] temperatures){
  int[] result=new int[temperatures.length];
  Stack<Integer> stack=new Stack<Integer>();
  for (int i=0; i < temperatures.length; i++) {
    while (!stack.isEmpty() && temperatures[i] > temperatures[stack.peek()]) {
      int index=stack.pop();
      result[index]=i - index;
    }
    stack.push(i);
  }
  return result;
}
