public static Collection<String> generateUniContext(CoNLLWord[] word,int i,int j){
  Collection<String> context=new LinkedList<String>();
  context.add(word[i].NAME + '?' + word[j].NAME);
  context.add(word[i].POSTAG + '?' + word[j].POSTAG);
  context.add(word[i].NAME + '?' + word[j].NAME + (i - j));
  context.add(word[i].POSTAG + '?' + word[j].POSTAG + (i - j));
  CoNLLWord wordBeforeI=i - 1 >= 0 ? word[i - 1] : CoNLLWord.NULL;
  CoNLLWord wordBeforeJ=j - 1 >= 0 ? word[j - 1] : CoNLLWord.NULL;
  context.add(wordBeforeI.NAME + '@' + word[i].NAME + '?' + word[j].NAME);
  context.add(word[i].NAME + '?' + wordBeforeJ.NAME + '@' + word[j].NAME);
  context.add(wordBeforeI.POSTAG + '@' + word[i].POSTAG + '?' + word[j].POSTAG);
  context.add(word[i].POSTAG + '?' + wordBeforeJ.POSTAG + '@' + word[j].POSTAG);
  return context;
}
