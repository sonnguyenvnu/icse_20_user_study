public int getLetterPosition(String letter){
  for (int i=0; i < getData().size(); i++) {
    if (getData().get(i).type == 1 && getData().get(i).pys.equals(letter)) {
      return i;
    }
  }
  return -1;
}
