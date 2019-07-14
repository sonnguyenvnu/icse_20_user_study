int pop(int stackNum) throws Exception {
  if (isEmpty(stackNum)) {
    throw new EmptyStackException();
  }
  int value=buffer[absTopOfStack(stackNum)];
  buffer[absTopOfStack(stackNum)]=0;
  stackPointer[stackNum]--;
  return value;
}
