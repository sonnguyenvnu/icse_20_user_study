private static void checkDateElements(List<Vertex> linkedArray){
  if (linkedArray.size() < 2)   return;
  ListIterator<Vertex> listIterator=linkedArray.listIterator();
  Vertex next=listIterator.next();
  Vertex current=next;
  while (listIterator.hasNext()) {
    next=listIterator.next();
    if (TextUtility.isAllNum(current.realWord) || TextUtility.isAllChineseNum(current.realWord)) {
      String nextWord=next.realWord;
      if ((nextWord.length() == 1 && "?????".contains(nextWord)) || (nextWord.length() == 2 && nextWord.equals("??"))) {
        mergeDate(listIterator,next,current);
      }
 else       if (nextWord.equals("?")) {
        if (TextUtility.isYearTime(current.realWord)) {
          mergeDate(listIterator,next,current);
        }
 else {
          current.confirmNature(Nature.m);
        }
      }
 else {
        if (current.realWord.endsWith("?")) {
          current.confirmNature(Nature.t,true);
        }
 else {
          char[] tmpCharArray=current.realWord.toCharArray();
          String lastChar=String.valueOf(tmpCharArray[tmpCharArray.length - 1]);
          if (!"?·??./".contains(lastChar)) {
            current.confirmNature(Nature.m,true);
          }
 else           if (current.realWord.length() > 1) {
            char last=current.realWord.charAt(current.realWord.length() - 1);
            current=Vertex.newNumberInstance(current.realWord.substring(0,current.realWord.length() - 1));
            listIterator.previous();
            listIterator.previous();
            listIterator.set(current);
            listIterator.next();
            listIterator.add(Vertex.newPunctuationInstance(String.valueOf(last)));
          }
        }
      }
    }
    current=next;
  }
}
