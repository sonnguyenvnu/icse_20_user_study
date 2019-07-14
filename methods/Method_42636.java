/** 
 * ??????????query_timestamp??????????? ???????XML???????????SSL?????
 * @return ??????
 * @throws IOException
 * @throws DocumentException
 * @throws MalformedURLException
 */
public static String query_timestamp() throws MalformedURLException, DocumentException, IOException {
  String strUrl=ALIPAY_GATEWAY_NEW + "service=query_timestamp&partner=" + AlipayConfigUtil.partner + "&_input_charset" + AlipayConfigUtil.input_charset;
  StringBuffer result=new StringBuffer();
  SAXReader reader=new SAXReader();
  Document doc=reader.read(new URL(strUrl).openStream());
  List<Node> nodeList=doc.selectNodes("//alipay/*");
  for (  Node node : nodeList) {
    if (node.getName().equals("is_success") && node.getText().equals("T")) {
      List<Node> nodeList1=doc.selectNodes("//response/timestamp/*");
      for (      Node node1 : nodeList1) {
        result.append(node1.getText());
      }
    }
  }
  return result.toString();
}
