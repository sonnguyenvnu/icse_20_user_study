/** 
 * Signs the specified  {@link SignableSAMLObject} with the specified {@link Credential} and{@code signatureAlgorithm}.
 */
static void sign(SignableSAMLObject signableObj,Credential signingCredential,String signatureAlgorithm){
  requireNonNull(signableObj,"signableObj");
  requireNonNull(signingCredential,"signingCredential");
  requireNonNull(signatureAlgorithm,"signatureAlgorithm");
  final Signature signature=signatureBuilder.buildObject();
  signature.setSignatureAlgorithm(signatureAlgorithm);
  signature.setSigningCredential(signingCredential);
  signature.setCanonicalizationAlgorithm(ALGO_ID_C14N_EXCL_OMIT_COMMENTS);
  try {
    signature.setKeyInfo(keyInfoGenerator.generate(signingCredential));
  }
 catch (  SecurityException e) {
    throw new SamlException("failed to create a key info of signing credential",e);
  }
  signableObj.setSignature(signature);
  serialize(signableObj);
  try {
    Signer.signObject(signature);
  }
 catch (  SignatureException e) {
    throw new SamlException("failed to sign a SAML object",e);
  }
}
