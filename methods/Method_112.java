void push(int stackNum,int value) throws Exception {
  if (stackPointer[stackNum] + 1 >= stackSize) {
    throw new FullStackException();
  }
  stackPointer[stackNum]++;
  buffer[absTopOfStack(stackNum)]=value;
}
