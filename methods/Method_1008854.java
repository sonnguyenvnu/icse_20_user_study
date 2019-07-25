/** 
 * Generate HTML for the specified content.<br> Don't expect this method to work, the conversion process relies on a structure  that has been preprocessed and is based on the complete document. Some examples  where this method probably fails with a NPE: <ul> <li>images</li> <li>fields</li> <li>bookmarks</li> <li>links</li> </ul>  
 * @param blockLevelContent
 * @return
 */
public org.w3c.dom.Document export(Object blockLevelContent,String cssClass,String cssId){
  HTMLConversionContext conversionContext=new HTMLConversionContext(htmlSettings,null,null);
  Document document=XmlUtils.neww3cDomDocument();
  Element parentNode=document.createElement("div");
  AbstractVisitorExporterGenerator<HTMLConversionContext> generator=null;
  if (cssClass != null) {
    parentNode.setAttribute("class",cssClass);
  }
  if (cssId != null) {
    parentNode.setAttribute("id",cssId);
  }
  document.appendChild(parentNode);
  generator=HTMLExporterVisitorGenerator.GENERATOR_FACTORY.createInstance(conversionContext,document,parentNode);
  new TraversalUtil(blockLevelContent,generator);
  return document;
}
