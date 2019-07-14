private void printWord(IWord word,StringBuilder sb,int id,int offset,boolean withComment){
  char delimiter='\t';
  char endLine='\n';
  sb.append('T').append(id).append(delimiter);
  sb.append(word.getLabel()).append(delimiter);
  int length=word.length();
  if (word instanceof CompoundWord) {
    length+=((CompoundWord)word).innerList.size() - 1;
  }
  sb.append(offset).append(delimiter).append(offset + length).append(delimiter);
  sb.append(word.getValue()).append(endLine);
  String translated=PartOfSpeechTagDictionary.translate(word.getLabel());
  if (withComment && !word.getLabel().equals(translated)) {
    sb.append('#').append(id).append(delimiter).append("AnnotatorNotes").append(delimiter).append('T').append(id).append(delimiter).append(translated).append(endLine);
  }
}
