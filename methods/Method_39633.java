/** 
 * Makes the given visitor visit the signature of this  {@link SignatureReader}. This signature is the one specified in the constructor (see  {@link #SignatureReader}). This method is intended to be called on a  {@link SignatureReader} that was created using a <i>JavaTypeSignature</i>, suchas the <code>signature</code> parameter of the  {@link ClassVisitor#visitField} or {@link MethodVisitor#visitLocalVariable} methods.
 * @param signatureVisitor the visitor that must visit this signature.
 */
public void acceptType(final SignatureVisitor signatureVisitor){
  parseType(signatureValue,0,signatureVisitor);
}
