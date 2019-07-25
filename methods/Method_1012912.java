private static void map(String actionUri,Class<? extends AutomatedAction> actionClass){
  ACTION_MAPPINGS.put(actionUri,actionClass);
}
