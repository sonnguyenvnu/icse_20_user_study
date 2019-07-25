protected void end(Object o){
  if (myCreationStack.isEmpty() || myCreationStack.peek() != o) {
    throw new RuntimeException("Unexpected end");
  }
  myCreationStack.pop();
  myEnds.put(o,getCurrentPosition());
}
