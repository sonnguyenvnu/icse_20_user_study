/** 
 * ?????
 * @param text
 * @param size
 * @return
 */
public static List<String> extract(String text,int size){
  IPhraseExtractor extractor=new MutualInformationEntropyPhraseExtractor();
  return extractor.extractPhrase(text,size);
}
