private static int subtraction26(char[] currentIndex,char[] beforeIndex){
  int result=0;
  Stack<Character> currentStack=new Stack<Character>();
  Stack<Character> berforStack=new Stack<Character>();
  for (int i=0; i < currentIndex.length; i++) {
    currentStack.push(currentIndex[i]);
  }
  for (int i=0; i < beforeIndex.length; i++) {
    berforStack.push(beforeIndex[i]);
  }
  int i=0;
  char beforechar='@';
  while (!currentStack.isEmpty()) {
    char currentChar=currentStack.pop();
    if (!berforStack.isEmpty()) {
      beforechar=berforStack.pop();
    }
    int n=currentChar - beforechar;
    if (n < 0) {
      n=n + 26;
      if (!currentStack.isEmpty()) {
        char borrow=currentStack.pop();
        char newBorrow=(char)(borrow - 1);
        currentStack.push(newBorrow);
      }
    }
    result+=n * Math.pow(26,i);
    i++;
    beforechar='@';
  }
  return result;
}
