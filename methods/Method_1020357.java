/** 
 * Returns a register list which is equivalent to the given one, except that it splits category-2 registers into two explicit entries. This returns the original list if no modification is required
 * @param orig {@code non-null;} the original list
 * @return {@code non-null;} the list with the described transformation
 */
private static RegisterSpecList explicitize(RegisterSpecList orig){
  int wordCount=wordCount(orig);
  int sz=orig.size();
  if (wordCount == sz) {
    return orig;
  }
  RegisterSpecList result=new RegisterSpecList(wordCount);
  int wordAt=0;
  for (int i=0; i < sz; i++) {
    RegisterSpec one=orig.get(i);
    result.set(wordAt,one);
    if (one.getCategory() == 2) {
      result.set(wordAt + 1,RegisterSpec.make(one.getReg() + 1,Type.VOID));
      wordAt+=2;
    }
 else {
      wordAt++;
    }
  }
  result.setImmutable();
  return result;
}
