@Override public ReadableMerchantStore update(PersistableMerchantStore store){
  Validate.notNull(store);
  MerchantStore mStore=mergePersistableMerchantStoreToMerchantStore(store,store.getCode(),languageService.defaultLanguage());
  updateMerchantStore(mStore);
  ReadableMerchantStore storeTO=getByCode(store.getCode(),languageService.defaultLanguage());
  return storeTO;
}
