private AjaxResponse logon(String userName,String password,String storeCode,HttpServletRequest request,HttpServletResponse response) throws Exception {
  AjaxResponse jsonObject=new AjaxResponse();
  try {
    LOG.debug("Authenticating user " + userName);
    MerchantStore store=(MerchantStore)request.getAttribute(Constants.MERCHANT_STORE);
    Language language=(Language)request.getAttribute("LANGUAGE");
    Customer customerModel=customerFacade.getCustomerByUserName(userName,store);
    if (customerModel == null) {
      jsonObject.setStatus(AjaxResponse.RESPONSE_STATUS_FAIURE);
      return jsonObject;
    }
    if (!customerModel.getMerchantStore().getCode().equals(storeCode)) {
      jsonObject.setStatus(AjaxResponse.RESPONSE_STATUS_FAIURE);
      return jsonObject;
    }
    customerFacade.authenticate(customerModel,userName,password);
    super.setSessionAttribute(Constants.CUSTOMER,customerModel,request);
    jsonObject.setStatus(AjaxResponse.RESPONSE_STATUS_SUCCESS);
    jsonObject.addEntry(Constants.RESPONSE_KEY_USERNAME,customerModel.getNick());
    LOG.info("Fetching and merging Shopping Cart data");
    String sessionShoppingCartCode=(String)request.getSession().getAttribute(Constants.SHOPPING_CART);
    if (!StringUtils.isBlank(sessionShoppingCartCode)) {
      ShoppingCart shoppingCart=customerFacade.mergeCart(customerModel,sessionShoppingCartCode,store,language);
      if (shoppingCart != null) {
        ShoppingCartData shoppingCartData=this.populateShoppingCartData(shoppingCart,store,language);
        if (shoppingCartData != null) {
          jsonObject.addEntry(Constants.SHOPPING_CART,shoppingCartData.getCode());
          request.getSession().setAttribute(Constants.SHOPPING_CART,shoppingCartData.getCode());
          Cookie c=new Cookie(Constants.COOKIE_NAME_CART,shoppingCartData.getCode());
          c.setMaxAge(60 * 24 * 3600);
          c.setPath(Constants.SLASH);
          response.addCookie(c);
        }
 else {
          Cookie c=new Cookie(Constants.COOKIE_NAME_CART,"");
          c.setMaxAge(0);
          c.setPath(Constants.SLASH);
          response.addCookie(c);
        }
      }
    }
 else {
      ShoppingCart cartModel=shoppingCartService.getByCustomer(customerModel);
      if (cartModel != null) {
        jsonObject.addEntry(Constants.SHOPPING_CART,cartModel.getShoppingCartCode());
        request.getSession().setAttribute(Constants.SHOPPING_CART,cartModel.getShoppingCartCode());
        Cookie c=new Cookie(Constants.COOKIE_NAME_CART,cartModel.getShoppingCartCode());
        c.setMaxAge(60 * 24 * 3600);
        c.setPath(Constants.SLASH);
        response.addCookie(c);
      }
    }
    StringBuilder cookieValue=new StringBuilder();
    cookieValue.append(store.getCode()).append("_").append(customerModel.getNick());
    Cookie c=new Cookie(Constants.COOKIE_NAME_USER,cookieValue.toString());
    c.setMaxAge(60 * 24 * 3600);
    c.setPath(Constants.SLASH);
    response.addCookie(c);
  }
 catch (  AuthenticationException ex) {
    jsonObject.setStatus(AjaxResponse.RESPONSE_STATUS_FAIURE);
  }
catch (  Exception e) {
    jsonObject.setStatus(AjaxResponse.RESPONSE_STATUS_FAIURE);
  }
  return jsonObject;
}
