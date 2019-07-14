/** 
 * ????????
 * @param data ??
 * @param dependency ??????
 * @param with_dependencies ???????????????????
 */
void transduce_instance_to_dependency(final Instance data,Dependency dependency,boolean with_dependencies){
  int L=data.forms.size();
  for (int i=0; i < L; ++i) {
    Integer form=forms_alphabet.idOf(data.forms.get(i));
    if (form == null) {
      form=forms_alphabet.idOf(SpecialOption.UNKNOWN);
    }
    Integer postag=postags_alphabet.idOf(data.postags.get(i));
    if (postag == null)     postag=postags_alphabet.idOf(SpecialOption.UNKNOWN);
    int deprel=(with_dependencies ? deprels_alphabet.idOf(data.deprels.get(i)) : -1);
    dependency.forms.add(form);
    dependency.postags.add(postag);
    dependency.heads.add(with_dependencies ? data.heads.get(i) : -1);
    dependency.deprels.add(with_dependencies ? deprel : -1);
  }
}
