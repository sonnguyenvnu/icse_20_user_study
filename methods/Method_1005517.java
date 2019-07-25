/** 
 * @deprecated see {@link #keys()}
 * @return array of {@link IClientConfigKey}
 */
@Deprecated public static IClientConfigKey[] values(){
  return keys().toArray(new IClientConfigKey[0]);
}
