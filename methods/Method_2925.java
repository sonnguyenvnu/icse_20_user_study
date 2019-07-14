/** 
 * ??(S0?S1?)????
 * @param ctx ???
 * @param nr_left_children ???????
 * @param nr_right_children ???????
 * @param features ????
 */
void get_valency_features(final Context ctx,final List<Integer> nr_left_children,final List<Integer> nr_right_children,List<Integer> features){
  if (!use_valency) {
    return;
  }
  int lvc=8;
  int rvc=8;
  if (ctx.S0 >= 0) {
    lvc=math.binned_1_2_3_4_5_6_10[nr_left_children.get(ctx.S0)];
    rvc=math.binned_1_2_3_4_5_6_10[nr_right_children.get(ctx.S0)];
    if (lvc == 10) {
      lvc=7;
    }
    if (rvc == 10) {
      rvc=7;
    }
  }
  features.add(lvc + kValencyInFeaturespace);
  features.add(rvc + kValencyInFeaturespace);
  lvc=8;
  rvc=8;
  if (ctx.S1 >= 0) {
    lvc=math.binned_1_2_3_4_5_6_10[nr_left_children.get(ctx.S1)];
    rvc=math.binned_1_2_3_4_5_6_10[nr_right_children.get(ctx.S1)];
    if (lvc == 10) {
      lvc=7;
    }
    if (rvc == 10) {
      rvc=7;
    }
  }
  features.add(lvc + kValencyInFeaturespace);
  features.add(rvc + kValencyInFeaturespace);
}
