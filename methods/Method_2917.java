/** 
 * ???????
 * @param data ????
 * @param cluster4
 * @param cluster6
 * @param cluster
 */
void get_cluster_from_dependency(final Dependency data,List<Integer> cluster4,List<Integer> cluster6,List<Integer> cluster){
  if (use_cluster) {
    int L=data.forms.size();
    for (int i=0; i < L; ++i) {
      int form=data.forms.get(i);
      cluster4.add(i == 0 ? cluster4_types_alphabet.idOf(SpecialOption.ROOT) : form_to_cluster4.get(form));
      cluster6.add(i == 0 ? cluster6_types_alphabet.idOf(SpecialOption.ROOT) : form_to_cluster6.get(form));
      cluster.add(i == 0 ? cluster_types_alphabet.idOf(SpecialOption.ROOT) : form_to_cluster.get(form));
    }
  }
}
