/** 
 * ?Mapper.xml?selectByExample??limit,offset
 */
@Override public boolean sqlMapSelectByExampleWithoutBLOBsElementGenerated(XmlElement element,IntrospectedTable introspectedTable){
  XmlElement ifLimitNotNullElement=new XmlElement("if");
  ifLimitNotNullElement.addAttribute(new Attribute("test","limit != null"));
  XmlElement ifOffsetNotNullElement=new XmlElement("if");
  ifOffsetNotNullElement.addAttribute(new Attribute("test","offset != null"));
  ifOffsetNotNullElement.addElement(new TextElement("limit ${offset}, ${limit}"));
  ifLimitNotNullElement.addElement(ifOffsetNotNullElement);
  XmlElement ifOffsetNullElement=new XmlElement("if");
  ifOffsetNullElement.addAttribute(new Attribute("test","offset == null"));
  ifOffsetNullElement.addElement(new TextElement("limit ${limit}"));
  ifLimitNotNullElement.addElement(ifOffsetNullElement);
  element.addElement(ifLimitNotNullElement);
  return true;
}
