/** 
 * {@inheritDoc}<p> May be extended by subclasses. </p>
 */
public void connect(IDocument document,boolean delayInitialization){
  Assert.isNotNull(document);
  Assert.isTrue(!document.containsPositionCategory(fPositionCategory));
  fDocument=document;
  fDocument.addPositionCategory(fPositionCategory);
  fIsInitialized=false;
  if (!delayInitialization)   checkInitialization();
}
