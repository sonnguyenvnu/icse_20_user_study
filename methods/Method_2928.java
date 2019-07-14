/** 
 * ???????
 * @param ctx ???
 * @param cluster4
 * @param cluster6
 * @param cluster
 * @param features ????
 */
void get_cluster_features(final Context ctx,final List<Integer> cluster4,final List<Integer> cluster6,final List<Integer> cluster,List<Integer> features){
  if (!use_cluster) {
    return;
  }
  PUSH(features,CLUSTER(cluster,ctx.S0));
  PUSH(features,CLUSTER4(cluster4,ctx.S0));
  PUSH(features,CLUSTER6(cluster6,ctx.S0));
  PUSH(features,CLUSTER(cluster,ctx.S1));
  PUSH(features,CLUSTER(cluster,ctx.S2));
  PUSH(features,CLUSTER(cluster,ctx.N0));
  PUSH(features,CLUSTER4(cluster4,ctx.N0));
  PUSH(features,CLUSTER6(cluster6,ctx.N0));
  PUSH(features,CLUSTER(cluster,ctx.N1));
  PUSH(features,CLUSTER(cluster,ctx.N2));
  PUSH(features,CLUSTER(cluster,ctx.S0L));
  PUSH(features,CLUSTER(cluster,ctx.S0R));
  PUSH(features,CLUSTER(cluster,ctx.S0L2));
  PUSH(features,CLUSTER(cluster,ctx.S0R2));
  PUSH(features,CLUSTER(cluster,ctx.S0LL));
  PUSH(features,CLUSTER(cluster,ctx.S0RR));
  PUSH(features,CLUSTER(cluster,ctx.S1L));
  PUSH(features,CLUSTER(cluster,ctx.S1R));
  PUSH(features,CLUSTER(cluster,ctx.S1L2));
  PUSH(features,CLUSTER(cluster,ctx.S1R2));
  PUSH(features,CLUSTER(cluster,ctx.S1LL));
  PUSH(features,CLUSTER(cluster,ctx.S1RR));
}
