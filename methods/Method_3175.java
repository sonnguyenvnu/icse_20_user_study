/** 
 * Delete removed documents from the internal container.
 */
void refresh(){
  ListIterator<Document<K>> listIterator=documents_.listIterator();
  while (listIterator.hasNext()) {
    if (listIterator.next() == null)     listIterator.remove();
  }
}
