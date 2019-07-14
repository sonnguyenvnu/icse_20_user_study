/** 
 * ?????????????
 */
void build_feature_space(){
  kFormInFeaturespace=0;
  kNilForm=forms_alphabet.idOf(SpecialOption.NIL);
  kFeatureSpaceEnd=forms_alphabet.size();
  kPostagInFeaturespace=kFeatureSpaceEnd;
  kNilPostag=kFeatureSpaceEnd + postags_alphabet.idOf(SpecialOption.NIL);
  kFeatureSpaceEnd+=postags_alphabet.size();
  kDeprelInFeaturespace=kFeatureSpaceEnd;
  kNilDeprel=kFeatureSpaceEnd + deprels_alphabet.idOf(SpecialOption.NIL);
  kFeatureSpaceEnd+=deprels_alphabet.size();
  kDistanceInFeaturespace=kFeatureSpaceEnd;
  kNilDistance=kFeatureSpaceEnd + (use_distance ? 8 : 0);
  kFeatureSpaceEnd+=(use_distance ? 9 : 0);
  kValencyInFeaturespace=kFeatureSpaceEnd;
  kNilValency=kFeatureSpaceEnd + (use_valency ? 8 : 0);
  kFeatureSpaceEnd+=(use_valency ? 9 : 0);
  kCluster4InFeaturespace=kFeatureSpaceEnd;
  if (use_cluster) {
    kNilCluster4=kFeatureSpaceEnd + cluster4_types_alphabet.idOf(SpecialOption.NIL);
    kFeatureSpaceEnd+=cluster4_types_alphabet.size();
  }
 else {
    kNilCluster4=kFeatureSpaceEnd;
  }
  kCluster6InFeaturespace=kFeatureSpaceEnd;
  if (use_cluster) {
    kNilCluster6=kFeatureSpaceEnd + cluster6_types_alphabet.idOf(SpecialOption.NIL);
    kFeatureSpaceEnd+=cluster6_types_alphabet.size();
  }
 else {
    kNilCluster6=kFeatureSpaceEnd;
  }
  kClusterInFeaturespace=kFeatureSpaceEnd;
  if (use_cluster) {
    kNilCluster=kFeatureSpaceEnd + cluster_types_alphabet.idOf(SpecialOption.NIL);
    kFeatureSpaceEnd+=cluster_types_alphabet.size();
  }
 else {
    kNilCluster=kFeatureSpaceEnd;
  }
}
