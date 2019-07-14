public static RestSettingBuilder get(final String id){
  return get(eq(checkId(id)));
}
