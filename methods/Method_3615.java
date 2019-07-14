@Override protected List<Term> segSentence(char[] sentence){
  if (sentence.length == 0) {
    return Collections.emptyList();
  }
  String original=new String(sentence);
  CharTable.normalization(sentence);
  String normalized=new String(sentence);
  List<String> wordList=new LinkedList<String>();
  List<CoreDictionary.Attribute> attributeList;
  attributeList=segmentWithAttribute(original,normalized,wordList);
  List<Term> termList=new ArrayList<Term>(wordList.size());
  int offset=0;
  for (  String word : wordList) {
    Term term=new Term(word,null);
    term.offset=offset;
    offset+=term.length();
    termList.add(term);
  }
  if (config.speechTagging) {
    if (posTagger != null) {
      String[] wordArray=new String[wordList.size()];
      offset=0;
      int id=0;
      for (      String word : wordList) {
        wordArray[id]=normalized.substring(offset,offset + word.length());
        ++id;
        offset+=word.length();
      }
      String[] posArray=tag(wordArray);
      Iterator<Term> iterator=termList.iterator();
      Iterator<CoreDictionary.Attribute> attributeIterator=attributeList == null ? null : attributeList.iterator();
      for (int i=0; i < posArray.length; i++) {
        if (attributeIterator != null && attributeIterator.hasNext()) {
          CoreDictionary.Attribute attribute=attributeIterator.next();
          if (attribute != null) {
            iterator.next().nature=attribute.nature[0];
            continue;
          }
        }
        iterator.next().nature=Nature.create(posArray[i]);
      }
      if (config.ner && neRecognizer != null) {
        List<Term> childrenList=null;
        if (config.isIndexMode()) {
          childrenList=new LinkedList<Term>();
          iterator=termList.iterator();
        }
        termList=new ArrayList<Term>(termList.size());
        String[] nerArray=recognize(wordArray,posArray);
        wordList.toArray(wordArray);
        StringBuilder result=new StringBuilder();
        result.append(wordArray[0]);
        if (childrenList != null) {
          childrenList.add(iterator.next());
        }
        if (attributeList != null) {
          attributeIterator=attributeList.iterator();
          for (int i=0; i < wordArray.length && attributeIterator.hasNext(); i++) {
            CoreDictionary.Attribute attribute=attributeIterator.next();
            if (attribute != null)             posArray[i]=attribute.nature[0].toString();
          }
        }
        String prePos=posArray[0];
        offset=0;
        for (int i=1; i < nerArray.length; i++) {
          NERTagSet tagSet=getNERTagSet();
          if (nerArray[i].charAt(0) == tagSet.B_TAG_CHAR || nerArray[i].charAt(0) == tagSet.S_TAG_CHAR || nerArray[i].charAt(0) == tagSet.O_TAG_CHAR) {
            Term term=new Term(result.toString(),Nature.create(prePos));
            term.offset=offset;
            offset+=term.length();
            termList.add(term);
            if (childrenList != null) {
              if (childrenList.size() > 1) {
                for (                Term shortTerm : childrenList) {
                  if (shortTerm.length() >= config.indexMode) {
                    termList.add(shortTerm);
                  }
                }
              }
              childrenList.clear();
            }
            result.setLength(0);
          }
          result.append(wordArray[i]);
          if (childrenList != null) {
            childrenList.add(iterator.next());
          }
          if (nerArray[i].charAt(0) == tagSet.O_TAG_CHAR || nerArray[i].charAt(0) == tagSet.S_TAG_CHAR) {
            prePos=posArray[i];
          }
 else {
            prePos=NERTagSet.posOf(nerArray[i]);
          }
        }
        if (result.length() != 0) {
          Term term=new Term(result.toString(),Nature.create(prePos));
          term.offset=offset;
          termList.add(term);
          if (childrenList != null) {
            if (childrenList.size() > 1) {
              for (              Term shortTerm : childrenList) {
                if (shortTerm.length() >= config.indexMode) {
                  termList.add(shortTerm);
                }
              }
            }
          }
        }
      }
    }
 else {
      for (      Term term : termList) {
        CoreDictionary.Attribute attribute=CoreDictionary.get(term.word);
        if (attribute != null) {
          term.nature=attribute.nature[0];
        }
 else {
          term.nature=Nature.n;
        }
      }
    }
  }
  if (config.translatedNameRecognize || config.japaneseNameRecognize) {
    List<Vertex> vertexList=toVertexList(termList,true);
    WordNet wordNetOptimum=new WordNet(sentence,vertexList);
    WordNet wordNetAll=wordNetOptimum;
    if (config.translatedNameRecognize) {
      TranslatedPersonRecognition.recognition(vertexList,wordNetOptimum,wordNetAll);
    }
    if (config.japaneseNameRecognize) {
      JapanesePersonRecognition.recognition(vertexList,wordNetOptimum,wordNetAll);
    }
    termList=convert(vertexList,config.offset);
  }
  return termList;
}
