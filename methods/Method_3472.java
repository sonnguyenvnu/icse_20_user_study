/** 
 * ????????????? ??????????????????????????????
 * @param term
 * @return
 */
public static CoreDictionary.Attribute guessAttribute(Term term){
  CoreDictionary.Attribute attribute=CoreDictionary.get(term.word);
  if (attribute == null) {
    attribute=CustomDictionary.get(term.word);
  }
  if (attribute == null) {
    if (term.nature != null) {
      if (Nature.nx == term.nature)       attribute=new CoreDictionary.Attribute(Nature.nx);
 else       if (Nature.m == term.nature)       attribute=CoreDictionary.get(CoreDictionary.M_WORD_ID);
    }
 else     if (term.word.trim().length() == 0)     attribute=new CoreDictionary.Attribute(Nature.x);
 else     attribute=new CoreDictionary.Attribute(Nature.nz);
  }
 else   term.nature=attribute.nature[0];
  return attribute;
}
