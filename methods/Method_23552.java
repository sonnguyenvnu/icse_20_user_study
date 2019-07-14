/** 
 * Parse a size that may have a suffix for its units. This assumes 90dpi, which implies, as given in the <A HREF="http://www.w3.org/TR/SVG/coords.html#Units">units</A> spec: <UL> <LI>"1pt" equals "1.25px" (and therefore 1.25 user units) <LI>"1pc" equals "15px" (and therefore 15 user units) <LI>"1mm" would be "3.543307px" (3.543307 user units) <LI>"1cm" equals "35.43307px" (and therefore 35.43307 user units) <LI>"1in" equals "90px" (and therefore 90 user units) </UL>
 * @param relativeTo (float) Used for %. When relative to viewbox, shouldbe svgWidth for horizontal dimentions, svgHeight for vertical, and svgXYSize for anything else.
 */
static protected float parseUnitSize(String text,float relativeTo){
  int len=text.length() - 2;
  if (text.endsWith("pt")) {
    return PApplet.parseFloat(text.substring(0,len)) * 1.25f;
  }
 else   if (text.endsWith("pc")) {
    return PApplet.parseFloat(text.substring(0,len)) * 15;
  }
 else   if (text.endsWith("mm")) {
    return PApplet.parseFloat(text.substring(0,len)) * 3.543307f;
  }
 else   if (text.endsWith("cm")) {
    return PApplet.parseFloat(text.substring(0,len)) * 35.43307f;
  }
 else   if (text.endsWith("in")) {
    return PApplet.parseFloat(text.substring(0,len)) * 90;
  }
 else   if (text.endsWith("px")) {
    return PApplet.parseFloat(text.substring(0,len));
  }
 else   if (text.endsWith("%")) {
    return relativeTo * parseFloatOrPercent(text);
  }
 else {
    return PApplet.parseFloat(text);
  }
}
