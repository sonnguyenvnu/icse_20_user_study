/** 
 * ??Analyzer?????????
 */
@Override protected TokenStreamComponents createComponents(String fieldName){
  Tokenizer _IKTokenizer=new IKTokenizer(configuration);
  return new TokenStreamComponents(_IKTokenizer);
}
