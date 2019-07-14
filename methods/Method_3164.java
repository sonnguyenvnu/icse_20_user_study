/** 
 * ????
 * @param text ??
 * @param size ???????
 * @return ????????? <= size
 */
public static List<String> extractPhrase(String text,int size){
  IPhraseExtractor extractor=new MutualInformationEntropyPhraseExtractor();
  return extractor.extractPhrase(text,size);
}
