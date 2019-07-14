@Override public void alignSelf(@Nullable YogaAlign alignSelf){
  mPrivateFlags|=PFLAG_ALIGN_SELF_IS_SET;
  getOrCreateObjectProps().append(INDEX_AlignSelf,alignSelf);
}
