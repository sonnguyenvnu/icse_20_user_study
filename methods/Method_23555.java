/** 
 * Get a particular element based on its SVG ID. When editing SVG by hand, this is the id="" tag on any SVG element. When editing from Illustrator, these IDs can be edited by expanding the layers palette. The names used in the layers palette, both for the layers or the shapes and groups beneath them can be used here. <PRE> // This code grabs "Layer 3" and the shapes beneath it. PShape layer3 = svg.getChild("Layer 3"); </PRE>
 */
@Override public PShape getChild(String name){
  PShape found=super.getChild(name);
  if (found == null) {
    found=super.getChild(name.replace(' ','_'));
  }
  if (found != null) {
    found.width=this.width;
    found.height=this.height;
  }
  return found;
}
