/** 
 * ????????
 * @param httpServletResponse
 */
@RequestMapping("orderQuery") public void orderQuery(HttpServletResponse httpServletResponse) throws IOException {
  String payKey=getString_UrlDecode_UTF8("payKey");
  String orderNO=getString_UrlDecode_UTF8("orderNO");
  OrderPayResultVo payResult=rpTradePaymentQueryService.getPayResult(payKey,orderNO);
  httpServletResponse.setContentType("text/text;charset=UTF-8");
  JsonUtils.responseJson(httpServletResponse,payResult);
}
