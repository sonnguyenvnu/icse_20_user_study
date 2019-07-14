public static CodeTransformer compose(CodeTransformer... transformers){
  return compose(ImmutableList.copyOf(transformers));
}
