private void checkMemberOffset(int memberOffset){
  if (memberOffset < 0 || sizeof() - memberOffset < POINTER_SIZE) {
    throw new IllegalArgumentException("Invalid member offset.");
  }
}
