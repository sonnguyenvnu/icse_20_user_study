/** 
 * Exit the current validation context <p>Here we remove the last schema URI visited; from then on, we have two scenarios:</p> <ul> <li>if the list of schema URIs is not empty, we do not take any further action;</li> <li>if the list is empty, validation of this part of the instance is complete; we therefore remove the tail of our  {@link #validationQueue validation queue} and change the current validation context.</li></ul> <p>Note that it is safe to pop the outermost validation context, since the first item in the validation queue is guaranteed to be  {@link #NULL_ELEMENT}.</p>
 */
void pop(){
  schemaURIs.removeLast();
  if (!schemaURIs.isEmpty())   return;
  final Element element=validationQueue.removeLast();
  pointer=element.pointer;
  schemaURIs=element.schemaURIs;
}
