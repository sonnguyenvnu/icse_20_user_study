/** 
 * Transcodes the ECDSA JWS signature into ASN.1/DER format for use by the JCA verifier.
 * @param jwsSignature The JWS signature, consisting of theconcatenated R and S values. Must not be {@code null}.
 * @return The ASN.1/DER encoded signature.
 * @throws GeneralSecurityException If the ECDSA JWS signature format is invalid.
 */
static byte[] transcodeSignatureToDER(byte[] jwsSignature) throws GeneralSecurityException {
  int rawLen=jwsSignature.length / 2;
  int i;
  for (i=rawLen; (i > 0) && (jwsSignature[rawLen - i] == 0); i--) {
  }
  int j=i;
  if (jwsSignature[rawLen - i] < 0) {
    j+=1;
  }
  int k;
  for (k=rawLen; (k > 0) && (jwsSignature[2 * rawLen - k] == 0); k--) {
  }
  int l=k;
  if (jwsSignature[2 * rawLen - k] < 0) {
    l+=1;
  }
  int len=2 + j + 2 + l;
  if (len > 255) {
    throw new GeneralSecurityException("Invalid ECDSA signature format");
  }
  int offset;
  final byte derSignature[];
  if (len < 128) {
    derSignature=new byte[2 + 2 + j + 2 + l];
    offset=1;
  }
 else {
    derSignature=new byte[3 + 2 + j + 2 + l];
    derSignature[1]=(byte)0x81;
    offset=2;
  }
  derSignature[0]=48;
  derSignature[offset++]=(byte)len;
  derSignature[offset++]=2;
  derSignature[offset++]=(byte)j;
  System.arraycopy(jwsSignature,rawLen - i,derSignature,(offset + j) - i,i);
  offset+=j;
  derSignature[offset++]=2;
  derSignature[offset++]=(byte)l;
  System.arraycopy(jwsSignature,2 * rawLen - k,derSignature,(offset + l) - k,k);
  return derSignature;
}
