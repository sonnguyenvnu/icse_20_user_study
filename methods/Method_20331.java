/** 
 * The type map is static so that models of the same class share the same views across different adapters. This is useful for view recycling when the adapter instance changes, or when there are multiple adapters. For testing purposes though it is good to be able to clear the map so we don't carry over values across tests.
 */
@VisibleForTesting void resetMapForTesting(){
  VIEW_TYPE_MAP.clear();
}
