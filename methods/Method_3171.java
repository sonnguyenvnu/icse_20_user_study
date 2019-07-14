/** 
 * Add the vectors of all documents to a composite vector.
 */
void set_composite_vector(){
  composite_.clear();
  for (  Document<K> document : documents_) {
    composite_.add_vector(document.feature());
  }
}
