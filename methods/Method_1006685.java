@RequestMapping(value="/public/{store}",method=RequestMethod.GET) @ResponseStatus(HttpStatus.ACCEPTED) @ResponseBody public AjaxResponse store(@PathVariable final String store,HttpServletRequest request,HttpServletResponse response){
  AjaxResponse ajaxResponse=new AjaxResponse();
  try {
    MerchantStore merchantStore=(MerchantStore)request.getAttribute(Constants.MERCHANT_STORE);
    if (merchantStore != null) {
      if (!merchantStore.getCode().equals(store)) {
        merchantStore=null;
      }
    }
    if (merchantStore == null) {
      merchantStore=merchantStoreService.getByCode(store);
    }
    if (merchantStore == null) {
      LOGGER.error("Merchant store is null for code " + store);
      response.sendError(503,"Merchant store is null for code " + store);
      return null;
    }
    Language language=merchantStore.getDefaultLanguage();
    Map<String,Language> langs=languageService.getLanguagesMap();
    return null;
  }
 catch (  Exception e) {
    LOGGER.error("Error while saving category",e);
    try {
      response.sendError(503,"Error while saving category " + e.getMessage());
    }
 catch (    Exception ignore) {
    }
    return null;
  }
}
