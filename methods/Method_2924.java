/** 
 * ??????
 * @param ctx ????
 * @param features ????
 */
void get_distance_features(final Context ctx,List<Integer> features){
  if (!use_distance) {
    return;
  }
  int dist=8;
  if (ctx.S0 >= 0 && ctx.S1 >= 0) {
    dist=math.binned_1_2_3_4_5_6_10[ctx.S0 - ctx.S1];
    if (dist == 10) {
      dist=7;
    }
  }
  features.add(dist + kDistanceInFeaturespace);
}
