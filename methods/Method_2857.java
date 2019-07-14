/** 
 * ????
 * @param word
 * @return
 */
public static IWord compile(IWord word){
  String label=word.getLabel();
  if ("nr".equals(label))   return new Word(word.getValue(),TAG_PEOPLE);
 else   if ("m".equals(label) || "mq".equals(label))   return new Word(word.getValue(),TAG_NUMBER);
 else   if ("t".equals(label))   return new Word(word.getValue(),TAG_TIME);
 else   if ("ns".equals(label))   return new Word(word.getValue(),TAG_PLACE);
  return word;
}
