public int addWord(String word){
  assert word != null;
  char[] charArray=word.toCharArray();
  Integer id=wordId.get(charArray);
  if (id == null) {
    id=wordId.size();
    wordId.put(charArray,id);
    idWord.add(word);
    assert idWord.size() == wordId.size();
  }
  return id;
}
