@Override protected void roleTag(List<List<IWord>> sentenceList){
  if (verbose)   System.out.println("??????");
  int i=0;
  for (  List<IWord> wordList : sentenceList) {
    if (verbose) {
      System.out.println(++i + " / " + sentenceList.size());
      System.out.println("???? " + wordList);
    }
    IWord pre=new Word("##?##","begin");
    ListIterator<IWord> listIterator=wordList.listIterator();
    while (listIterator.hasNext()) {
      IWord word=listIterator.next();
      if (!word.getLabel().equals(Nature.nr.toString())) {
        word.setLabel(NR.A.toString());
      }
 else {
        if (!pre.getLabel().equals(Nature.nr.toString()) && !pre.getValue().equals(Predefine.TAG_BIGIN)) {
          pre.setLabel(NR.K.toString());
        }
      }
      pre=word;
    }
    if (verbose)     System.out.println("???? " + wordList);
    IWord next=new Word("##?##","end");
    while (listIterator.hasPrevious()) {
      IWord word=listIterator.previous();
      if (word.getLabel().equals(Nature.nr.toString())) {
        String label=next.getLabel();
        if (label.equals("A"))         next.setLabel("L");
 else         if (label.equals("K"))         next.setLabel("M");
      }
      next=word;
    }
    if (verbose)     System.out.println("???? " + wordList);
    listIterator=wordList.listIterator();
    while (listIterator.hasNext()) {
      IWord word=listIterator.next();
      if (word.getLabel().equals(Nature.nr.toString())) {
switch (word.getValue().length()) {
case 2:
          if (word.getValue().startsWith("?") || word.getValue().startsWith("?") || word.getValue().startsWith("?")) {
            listIterator.add(new Word(word.getValue().substring(1,2),NR.B.toString()));
            word.setValue(word.getValue().substring(0,1));
            word.setLabel(NR.F.toString());
          }
 else           if (word.getValue().endsWith("?") || word.getValue().endsWith("?") || word.getValue().endsWith("?") || word.getValue().endsWith("?") || word.getValue().endsWith("?") || word.getValue().endsWith("?") || word.getValue().endsWith("?") || word.getValue().endsWith("?")) {
            listIterator.add(new Word(word.getValue().substring(1,2),NR.G.toString()));
            word.setValue(word.getValue().substring(0,1));
            word.setLabel(NR.B.toString());
          }
 else {
            listIterator.add(new Word(word.getValue().substring(1,2),NR.E.toString()));
            word.setValue(word.getValue().substring(0,1));
            word.setLabel(NR.B.toString());
          }
        break;
case 3:
      listIterator.add(new Word(word.getValue().substring(1,2),NR.C.toString()));
    listIterator.add(new Word(word.getValue().substring(2,3),NR.D.toString()));
  word.setValue(word.getValue().substring(0,1));
word.setLabel(NR.B.toString());
break;
default :
word.setLabel(NR.A.toString());
}
}
}
if (verbose) System.out.println("???? " + wordList);
listIterator=wordList.listIterator();
pre=new Word("##?##","begin");
while (listIterator.hasNext()) {
IWord word=listIterator.next();
if (word.getLabel().equals(NR.B.toString())) {
String combine=pre.getValue() + word.getValue();
if (dictionary.contains(combine)) {
pre.setValue(combine);
pre.setLabel("U");
listIterator.remove();
}
}
pre=word;
}
if (verbose) System.out.println("???? " + wordList);
next=new Word("##?##","end");
while (listIterator.hasPrevious()) {
IWord word=listIterator.previous();
if (word.getLabel().equals(NR.B.toString())) {
String combine=word.getValue() + next.getValue();
if (dictionary.contains(combine)) {
next.setValue(combine);
next.setLabel(next.getLabel().equals(NR.C.toString()) ? NR.X.toString() : NR.Y.toString());
listIterator.remove();
}
}
next=word;
}
if (verbose) System.out.println("???? " + wordList);
pre=new Word("##?##","begin");
while (listIterator.hasNext()) {
IWord word=listIterator.next();
if (word.getLabel().equals(NR.D.toString())) {
String combine=pre.getValue() + word.getValue();
if (dictionary.contains(combine)) {
pre.setValue(combine);
pre.setLabel(NR.Z.toString());
listIterator.remove();
}
}
pre=word;
}
if (verbose) System.out.println("???? " + wordList);
next=new Word("##?##","end");
while (listIterator.hasPrevious()) {
IWord word=listIterator.previous();
if (word.getLabel().equals(NR.D.toString())) {
String combine=word.getValue() + next.getValue();
if (dictionary.contains(combine)) {
next.setValue(combine);
next.setLabel(NR.V.toString());
listIterator.remove();
}
}
next=word;
}
if (verbose) System.out.println("???? " + wordList);
LinkedList<IWord> wordLinkedList=(LinkedList<IWord>)wordList;
wordLinkedList.addFirst(new Word(Predefine.TAG_BIGIN,"S"));
wordLinkedList.addLast(new Word(Predefine.TAG_END,"A"));
if (verbose) System.out.println("???? " + wordList);
}
}
