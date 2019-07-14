private void checkPointer(int pointer){
  if (pointer < 0 || size < pointer) {
    throw new IndexOutOfBoundsException("Invalid stack pointer");
  }
}
