static private PFont parseFont(XML properties){
  String fontFamily=null;
  float size=10;
  int weight=PLAIN;
  int italic=0;
  if (properties.hasAttribute("style")) {
    String styleText=properties.getString("style");
    String[] styleTokens=PApplet.splitTokens(styleText,";");
    for (int i=0; i < styleTokens.length; i++) {
      String[] tokens=PApplet.splitTokens(styleTokens[i],":");
      tokens[0]=PApplet.trim(tokens[0]);
      if (tokens[0].equals("font-style")) {
        if (tokens[1].contains("italic")) {
          italic=ITALIC;
        }
      }
 else       if (tokens[0].equals("font-variant")) {
      }
 else       if (tokens[0].equals("font-weight")) {
        if (tokens[1].contains("bold")) {
          weight=BOLD;
        }
      }
 else       if (tokens[0].equals("font-stretch")) {
      }
 else       if (tokens[0].equals("font-size")) {
        size=Float.parseFloat(tokens[1].split("px")[0]);
      }
 else       if (tokens[0].equals("line-height")) {
      }
 else       if (tokens[0].equals("font-family")) {
        fontFamily=tokens[1];
      }
 else       if (tokens[0].equals("text-align")) {
      }
 else       if (tokens[0].equals("letter-spacing")) {
      }
 else       if (tokens[0].equals("word-spacing")) {
      }
 else       if (tokens[0].equals("writing-mode")) {
      }
 else       if (tokens[0].equals("text-anchor")) {
      }
 else {
      }
    }
  }
  if (fontFamily == null) {
    return null;
  }
  return createFont(fontFamily,weight | italic,size,true);
}
