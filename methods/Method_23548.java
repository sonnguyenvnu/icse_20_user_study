protected void parseColors(XML properties){
  if (properties.hasAttribute("opacity")) {
    String opacityText=properties.getString("opacity");
    setOpacity(opacityText);
  }
  if (properties.hasAttribute("stroke")) {
    String strokeText=properties.getString("stroke");
    setColor(strokeText,false);
  }
  if (properties.hasAttribute("stroke-opacity")) {
    String strokeOpacityText=properties.getString("stroke-opacity");
    setStrokeOpacity(strokeOpacityText);
  }
  if (properties.hasAttribute("stroke-width")) {
    String lineweight=properties.getString("stroke-width");
    setStrokeWeight(lineweight);
  }
  if (properties.hasAttribute("stroke-linejoin")) {
    String linejoin=properties.getString("stroke-linejoin");
    setStrokeJoin(linejoin);
  }
  if (properties.hasAttribute("stroke-linecap")) {
    String linecap=properties.getString("stroke-linecap");
    setStrokeCap(linecap);
  }
  if (properties.hasAttribute("fill")) {
    String fillText=properties.getString("fill");
    setColor(fillText,true);
  }
  if (properties.hasAttribute("fill-opacity")) {
    String fillOpacityText=properties.getString("fill-opacity");
    setFillOpacity(fillOpacityText);
  }
  if (properties.hasAttribute("style")) {
    String styleText=properties.getString("style");
    String[] styleTokens=PApplet.splitTokens(styleText,";");
    for (int i=0; i < styleTokens.length; i++) {
      String[] tokens=PApplet.splitTokens(styleTokens[i],":");
      tokens[0]=PApplet.trim(tokens[0]);
      if (tokens[0].equals("fill")) {
        setColor(tokens[1],true);
      }
 else       if (tokens[0].equals("fill-opacity")) {
        setFillOpacity(tokens[1]);
      }
 else       if (tokens[0].equals("stroke")) {
        setColor(tokens[1],false);
      }
 else       if (tokens[0].equals("stroke-width")) {
        setStrokeWeight(tokens[1]);
      }
 else       if (tokens[0].equals("stroke-linecap")) {
        setStrokeCap(tokens[1]);
      }
 else       if (tokens[0].equals("stroke-linejoin")) {
        setStrokeJoin(tokens[1]);
      }
 else       if (tokens[0].equals("stroke-opacity")) {
        setStrokeOpacity(tokens[1]);
      }
 else       if (tokens[0].equals("opacity")) {
        setOpacity(tokens[1]);
      }
 else {
      }
    }
  }
}
