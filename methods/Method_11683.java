public static Selector getSelector(ExtractBy extractBy){
  String value=extractBy.value();
  Selector selector;
switch (extractBy.type()) {
case Css:
    selector=new CssSelector(value);
  break;
case Regex:
selector=new RegexSelector(value);
break;
case XPath:
selector=new XpathSelector(value);
break;
case JsonPath:
selector=new JsonPathSelector(value);
break;
default :
selector=new XpathSelector(value);
}
return selector;
}
