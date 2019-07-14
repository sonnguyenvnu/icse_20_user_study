/** 
 * Returns true if the parameter supports at least one of the following: <ul> <li>cell-level TTL  {@link StoreFeatures#hasCellTTL()}</li> <li>store-level TTL  {@link StoreFeatures#hasStoreTTL()}</li> </ul>
 * @param features an arbitrary {@code StoreFeatures} instance
 * @return true if and only if at least one TTL mode is supported
 */
public static boolean supportsAnyTTL(StoreFeatures features){
  return features.hasCellTTL() || features.hasStoreTTL();
}
