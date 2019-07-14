public static @NonNull Map<String,Object> categoryProperties(final @NonNull Category category,final @NonNull String prefix){
  final Map<String,Object> properties=new HashMap<String,Object>(){
{
      put("id",category.id());
      put("name",String.valueOf(category.name()));
    }
  }
;
  return MapUtils.prefixKeys(properties,prefix);
}
