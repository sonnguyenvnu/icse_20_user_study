/** 
 * Replace each key found in the string with its corresponding value.
 */
public @NonNull String format(final @NonNull String string,final @NonNull String key1,final @Nullable String value1,final @NonNull String key2,final @Nullable String value2){
  final Map<String,String> substitutions=new HashMap<String,String>(){
{
      put(key1,value1);
      put(key2,value2);
    }
  }
;
  return replace(string,substitutions);
}
