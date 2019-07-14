public static KeyRange transformRange(Token leftKeyExclusive,Token rightKeyInclusive){
  if (!(leftKeyExclusive instanceof BytesToken))   throw new UnsupportedOperationException();
  assert rightKeyInclusive instanceof BytesToken;
  BytesToken l=(BytesToken)leftKeyExclusive;
  BytesToken r=(BytesToken)rightKeyInclusive;
  byte[] leftTokenValue=(byte[])l.getTokenValue();
  byte[] rightTokenValue=(byte[])r.getTokenValue();
  Preconditions.checkArgument(leftTokenValue.length == rightTokenValue.length,"Tokens have unequal length");
  int tokenLength=leftTokenValue.length;
  byte[][] tokens=new byte[][]{leftTokenValue,rightTokenValue};
  byte[][] plusOne=new byte[2][tokenLength];
  for (int j=0; j < 2; j++) {
    boolean carry=true;
    for (int i=tokenLength - 1; i >= 0; i--) {
      byte b=tokens[j][i];
      if (carry) {
        b++;
        carry=b == 0;
      }
      plusOne[j][i]=b;
    }
  }
  StaticBuffer lb=StaticArrayBuffer.of(plusOne[0]);
  StaticBuffer rb=StaticArrayBuffer.of(plusOne[1]);
  Preconditions.checkArgument(lb.length() == tokenLength,lb.length());
  Preconditions.checkArgument(rb.length() == tokenLength,rb.length());
  return new KeyRange(lb,rb);
}
