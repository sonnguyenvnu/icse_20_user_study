/** 
 * Return the <it>i</it><sup>th</sup> <code> {@link Transform}</code>. Valid <code>i</code> values are 0 to <code> {@link #getLength}-1</code>.
 * @param i index of {@link Transform} to return
 * @return the <it>i</it><sup>th</sup> Transform
 * @throws TransformationException
 */
public Transform item(int i) throws TransformationException {
  try {
    initTransforms();
    return new Transform(transforms[i],this.baseURI);
  }
 catch (  XMLSecurityException ex) {
    throw new TransformationException(ex);
  }
}
