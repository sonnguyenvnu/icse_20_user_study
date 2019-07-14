/** 
 * Returns a map of public static final integer fields in the specified classes, to their String representations. An optional filter can be specified to only include specific fields. The target map may be null, in which case a new map is allocated and returned. <p>This method is useful when debugging to quickly identify values returned from an API.</p>
 * @param filter       the filter to use (optional)
 * @param target       the target map (optional)
 * @param tokenClasses the classes to get tokens from
 * @return the token map
 */
public static Map<Integer,String> apiClassTokens(@Nullable BiPredicate<Field,Integer> filter,@Nullable Map<Integer,String> target,Class<?>... tokenClasses){
  if (target == null) {
    target=new HashMap<>(64);
  }
  int TOKEN_MODIFIERS=Modifier.PUBLIC | Modifier.STATIC | Modifier.FINAL;
  for (  Class<?> tokenClass : tokenClasses) {
    if (tokenClass == null) {
      continue;
    }
    for (    Field field : tokenClass.getDeclaredFields()) {
      if ((field.getModifiers() & TOKEN_MODIFIERS) == TOKEN_MODIFIERS && field.getType() == int.class) {
        try {
          Integer value=field.getInt(null);
          if (filter != null && !filter.test(field,value)) {
            continue;
          }
          String name=target.get(value);
          target.put(value,name == null ? field.getName() : name + "|" + field.getName());
        }
 catch (        IllegalAccessException e) {
        }
      }
    }
  }
  return target;
}
