/** 
 * Remove a document from this cluster.
 * @param index the index of vector container of documents
 */
void remove_document(int index){
  ListIterator<Document<K>> listIterator=documents_.listIterator(index);
  Document<K> document=listIterator.next();
  listIterator.set(null);
  composite_.sub_vector(document.feature());
}
