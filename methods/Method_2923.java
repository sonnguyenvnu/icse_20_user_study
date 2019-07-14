/** 
 * ??????
 * @param ctx ???
 * @param forms ??
 * @param postags ??
 * @param deprels ??
 * @param features ?????????
 */
void get_basic_features(final Context ctx,final List<Integer> forms,final List<Integer> postags,final List<Integer> deprels,List<Integer> features){
  PUSH(features,FORM(forms,ctx.S0));
  PUSH(features,POSTAG(postags,ctx.S0));
  PUSH(features,FORM(forms,ctx.S1));
  PUSH(features,POSTAG(postags,ctx.S1));
  PUSH(features,FORM(forms,ctx.S2));
  PUSH(features,POSTAG(postags,ctx.S2));
  PUSH(features,FORM(forms,ctx.N0));
  PUSH(features,POSTAG(postags,ctx.N0));
  PUSH(features,FORM(forms,ctx.N1));
  PUSH(features,POSTAG(postags,ctx.N1));
  PUSH(features,FORM(forms,ctx.N2));
  PUSH(features,POSTAG(postags,ctx.N2));
  PUSH(features,FORM(forms,ctx.S0L));
  PUSH(features,POSTAG(postags,ctx.S0L));
  PUSH(features,DEPREL(deprels,ctx.S0L));
  PUSH(features,FORM(forms,ctx.S0R));
  PUSH(features,POSTAG(postags,ctx.S0R));
  PUSH(features,DEPREL(deprels,ctx.S0R));
  PUSH(features,FORM(forms,ctx.S0L2));
  PUSH(features,POSTAG(postags,ctx.S0L2));
  PUSH(features,DEPREL(deprels,ctx.S0L2));
  PUSH(features,FORM(forms,ctx.S0R2));
  PUSH(features,POSTAG(postags,ctx.S0R2));
  PUSH(features,DEPREL(deprels,ctx.S0R2));
  PUSH(features,FORM(forms,ctx.S0LL));
  PUSH(features,POSTAG(postags,ctx.S0LL));
  PUSH(features,DEPREL(deprels,ctx.S0LL));
  PUSH(features,FORM(forms,ctx.S0RR));
  PUSH(features,POSTAG(postags,ctx.S0RR));
  PUSH(features,DEPREL(deprels,ctx.S0RR));
  PUSH(features,FORM(forms,ctx.S1L));
  PUSH(features,POSTAG(postags,ctx.S1L));
  PUSH(features,DEPREL(deprels,ctx.S1L));
  PUSH(features,FORM(forms,ctx.S1R));
  PUSH(features,POSTAG(postags,ctx.S1R));
  PUSH(features,DEPREL(deprels,ctx.S1R));
  PUSH(features,FORM(forms,ctx.S1L2));
  PUSH(features,POSTAG(postags,ctx.S1L2));
  PUSH(features,DEPREL(deprels,ctx.S1L2));
  PUSH(features,FORM(forms,ctx.S1R2));
  PUSH(features,POSTAG(postags,ctx.S1R2));
  PUSH(features,DEPREL(deprels,ctx.S1R2));
  PUSH(features,FORM(forms,ctx.S1LL));
  PUSH(features,POSTAG(postags,ctx.S1LL));
  PUSH(features,DEPREL(deprels,ctx.S1LL));
  PUSH(features,FORM(forms,ctx.S1RR));
  PUSH(features,POSTAG(postags,ctx.S1RR));
  PUSH(features,DEPREL(deprels,ctx.S1RR));
}
