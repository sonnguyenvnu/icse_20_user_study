public static <T extends ConfigApplier<T>>ImmutableList<T> configItems(final List<T> items,final MocoConfig... configs){
  return from(items).transform(Configs.<T>config(configs)).toList();
}
