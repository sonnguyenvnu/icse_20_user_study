/** 
 * replaces this instance with ether StaticReference or with DynamicReference. (only static so far) removes reference in case of error.
 */
public void replace(){
  getSourceNode().setReference(getLink(),myReplacementReference);
}
