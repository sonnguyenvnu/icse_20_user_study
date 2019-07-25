private static String extract(String documentName,File f,String target) throws Exception {
  String xml=IOUtils.readStringAndClose(new InputStreamReader(new FileInputStream(f),StandardCharsets.UTF_8),-1);
  StringBuilder template=new StringBuilder(xml.length());
  int id=0;
  SortedProperties prop=new SortedProperties();
  XMLParser parser=new XMLParser(xml);
  StringBuilder buff=new StringBuilder();
  Stack<String> stack=new Stack<>();
  String tag="";
  boolean ignoreEnd=false;
  String nextKey="";
  boolean templateIsCopy=false;
  while (true) {
    int event=parser.next();
    if (event == XMLParser.END_DOCUMENT) {
      break;
    }
 else     if (event == XMLParser.CHARACTERS) {
      String s=parser.getText();
      if (s.trim().length() == 0) {
        if (buff.length() > 0) {
          buff.append(s);
        }
 else {
          template.append(s);
        }
      }
 else       if ("p".equals(tag) || "li".equals(tag) || "a".equals(tag) || "td".equals(tag) || "th".equals(tag) || "h1".equals(tag) || "h2".equals(tag) || "h3".equals(tag) || "h4".equals(tag) || "body".equals(tag) || "b".equals(tag) || "code".equals(tag) || "form".equals(tag) || "span".equals(tag) || "em".equals(tag) || "div".equals(tag) || "strong".equals(tag) || "label".equals(tag)) {
        if (buff.length() == 0) {
          nextKey=documentName + "_" + (1000 + id++) + "_" + tag;
          template.append(getSpace(s,true));
        }
 else         if (templateIsCopy) {
          buff.append(getSpace(s,true));
        }
        buff.append(s);
      }
 else       if ("pre".equals(tag) || "title".equals(tag) || "script".equals(tag) || "style".equals(tag)) {
        template.append(s);
      }
 else {
        System.out.println(f.getName() + " invalid wrapper tag for text: " + tag + " text: " + s);
        System.out.println(parser.getRemaining());
        throw new Exception();
      }
    }
 else     if (event == XMLParser.START_ELEMENT) {
      stack.add(tag);
      String name=parser.getName();
      if ("code".equals(name) || "a".equals(name) || "b".equals(name) || "span".equals(name)) {
        if (buff.length() > 0) {
          buff.append(parser.getToken());
          ignoreEnd=false;
        }
 else {
          ignoreEnd=true;
          template.append(parser.getToken());
        }
      }
 else       if ("p".equals(tag) || "li".equals(tag) || "td".equals(tag) || "th".equals(tag) || "h1".equals(tag) || "h2".equals(tag) || "h3".equals(tag) || "h4".equals(tag) || "body".equals(tag) || "form".equals(tag)) {
        if (buff.length() > 0) {
          if (templateIsCopy) {
            template.append(buff.toString());
          }
 else {
            template.append("${" + nextKey + "}");
          }
          add(prop,nextKey,buff);
        }
        template.append(parser.getToken());
      }
 else {
        template.append(parser.getToken());
      }
      tag=name;
    }
 else     if (event == XMLParser.END_ELEMENT) {
      String name=parser.getName();
      if ("code".equals(name) || "a".equals(name) || "b".equals(name) || "span".equals(name) || "em".equals(name) || "strong".equals(name)) {
        if (ignoreEnd) {
          if (buff.length() > 0) {
            if (templateIsCopy) {
              template.append(buff.toString());
            }
 else {
              template.append("${" + nextKey + "}");
            }
            add(prop,nextKey,buff);
          }
          template.append(parser.getToken());
        }
 else {
          if (buff.length() > 0) {
            buff.append(parser.getToken());
          }
        }
      }
 else {
        if (buff.length() > 0) {
          if (templateIsCopy) {
            template.append(buff.toString());
          }
 else {
            template.append("${" + nextKey + "}");
          }
          add(prop,nextKey,buff);
        }
        template.append(parser.getToken());
      }
      tag=stack.pop();
    }
 else     if (event == XMLParser.DTD) {
      template.append(parser.getToken());
    }
 else     if (event == XMLParser.COMMENT) {
      template.append(parser.getToken());
    }
 else {
      int eventType=parser.getEventType();
      throw new Exception("Unexpected event " + eventType + " at " + parser.getRemaining());
    }
  }
  new File(target).mkdirs();
  String propFileName=target + "/_docs_" + MAIN_LANGUAGE + ".properties";
  Properties old=load(propFileName,false);
  prop.putAll(old);
  store(prop,propFileName,false);
  String t=template.toString();
  if (templateIsCopy && !t.equals(xml)) {
    for (int i=0; i < Math.min(t.length(),xml.length()); i++) {
      if (t.charAt(i) != xml.charAt(i)) {
        int start=Math.max(0,i - 30), end=Math.min(i + 30,xml.length());
        t=t.substring(start,end);
        xml=xml.substring(start,end);
      }
    }
    System.out.println("xml--------------------------------------------------: ");
    System.out.println(xml);
    System.out.println("t---------------------------------------------------: ");
    System.out.println(t);
    System.exit(1);
  }
  return t;
}
