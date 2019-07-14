public static Collection<String> generateSingleWordContext(CoNLLWord[] word,int index,String mark){
  Collection<String> context=new LinkedList<String>();
  for (int i=index - 2; i < index + 2 + 1; ++i) {
    CoNLLWord w=i >= 0 && i < word.length ? word[i] : CoNLLWord.NULL;
    context.add(w.NAME + mark + (i - index));
    context.add(w.POSTAG + mark + (i - index));
  }
  return context;
}
