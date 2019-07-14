private void addAccessOrderWindowDeque(){
  if (Feature.usesAccessOrderWindowDeque(context.parentFeatures) || !Feature.usesAccessOrderWindowDeque(context.generateFeatures)) {
    return;
  }
  context.constructor.addStatement("this.$L = builder.evicts() || builder.expiresAfterAccess()\n? new $T()\n: null","accessOrderWindowDeque",ACCESS_ORDER_DEQUE);
  addFieldAndMethod(ACCESS_ORDER_DEQUE,"accessOrderWindowDeque");
}
