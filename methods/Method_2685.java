@Override public Iterator<CoNLLWord> iterator(){
  return new Iterator<CoNLLWord>(){
    @Override public boolean hasNext(){
      return index < word.length;
    }
    @Override public CoNLLWord next(){
      return word[index++];
    }
    @Override public void remove(){
      throw new UnsupportedOperationException("CoNLLSentence???????????");
    }
  }
;
}
