/** 
 * This method tests whether a particle par is a subset of this particle.
 */
public final boolean contains(TBPar par){
  for (int i=0; i < par.size(); i++) {
    if (!this.member(par.exprAt(i))) {
      return false;
    }
  }
  return true;
}
