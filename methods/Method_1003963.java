/** 
 * Other form of deserialize for a ByteArrayInputStream.
 */
public final TwitterTokenStream deserialize(final ByteArrayInputStream bais,final CharSequence charSequence) throws IOException {
  final AttributeInputStream input=new AttributeInputStream(bais);
  TwitterTokenStream twitterTokenStream=new TwitterTokenStream(){
    @Override public final boolean incrementToken(){
      if (token < numTokens) {
        token++;
        try {
          deserializeAttributes(input,chars);
        }
 catch (        IOException e) {
          throw new RuntimeException(e);
        }
        return true;
      }
      return false;
    }
    @Override public void reset(){
      CharSequence newChars=inputCharSequence();
      Preconditions.checkArgument(newChars == null || newChars == chars,"this TwitterTokenStream does not do actual tokenization and only supports reset(null)");
      try {
        input.reset();
        bais.reset();
        version=readVersionAndCheckFingerprint(input,attributeSerializersFingerprint);
        numTokens=input.readVInt();
        for (        AttributeSerializer deserializer : attributeSerializers) {
          deserializer.initialize(this,version);
        }
      }
 catch (      IOException e) {
        throw new IllegalStateException("Unexpected exception, but...",e);
      }
      token=0;
    }
  }
;
  twitterTokenStream.reset(null);
  return twitterTokenStream;
}
