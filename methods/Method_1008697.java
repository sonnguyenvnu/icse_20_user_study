/** 
 * Returns a DocIdSet per segments containing the matching docs for the specified slice.
 */
private DocIdSet build(LeafReader reader) throws IOException {
  final DocIdSetBuilder builder=new DocIdSetBuilder(reader.maxDoc());
  final Terms terms=reader.terms(getField());
  final TermsEnum te=terms.iterator();
  PostingsEnum docsEnum=null;
  for (BytesRef term=te.next(); term != null; term=te.next()) {
    int hashCode=StringHelper.murmurhash3_x86_32(term,SEED);
    if (contains(hashCode)) {
      docsEnum=te.postings(docsEnum,PostingsEnum.NONE);
      builder.add(docsEnum);
    }
  }
  return builder.build();
}
