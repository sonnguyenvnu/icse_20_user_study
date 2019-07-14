/** 
 * Sets an abstract type in  {@link #currentFrame}.
 * @param frameIndex the index of the element to be set in {@link #currentFrame}.
 * @param abstractType an abstract type.
 */
void visitAbstractType(final int frameIndex,final int abstractType){
  currentFrame[frameIndex]=abstractType;
}
