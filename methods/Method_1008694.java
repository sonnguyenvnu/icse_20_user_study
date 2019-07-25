/** 
 * Modifies incoming TopDocs (in) by replacing the top hits with resorted's hits, and then resorting all hits. 
 */
private TopDocs combine(TopDocs in,TopDocs resorted,QueryRescoreContext ctx){
  System.arraycopy(resorted.scoreDocs,0,in.scoreDocs,0,resorted.scoreDocs.length);
  if (in.scoreDocs.length > resorted.scoreDocs.length) {
    for (int i=resorted.scoreDocs.length; i < in.scoreDocs.length; i++) {
      in.scoreDocs[i].score*=ctx.queryWeight();
    }
    Arrays.sort(in.scoreDocs,SCORE_DOC_COMPARATOR);
  }
  in.setMaxScore(in.scoreDocs[0].score);
  return in;
}
