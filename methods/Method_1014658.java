@Override public void paint(Graphics2D g){
  ST styler=chart.getStyler();
  g.setFont(styler.getAxisTickLabelsFont());
  g.setColor(styler.getAxisTickLabelsColor());
  if (direction == Axis.Direction.Y && styler.isYAxisTicksVisible()) {
    boolean onRight=styler.getYAxisGroupPosistion(yAxis.getYIndex()) == YAxisPosition.Right;
    double xOffset;
    if (onRight) {
      xOffset=yAxis.getBounds().getX() + (styler.isYAxisTicksVisible() ? styler.getAxisTickMarkLength() + styler.getAxisTickPadding() : 0);
    }
 else {
      double xWidth=yAxis.getAxisTitle().getBounds().getWidth();
      xOffset=yAxis.getAxisTitle().getBounds().getX() + xWidth;
    }
    double yOffset=yAxis.getBounds().getY();
    double height=yAxis.getBounds().getHeight();
    double maxTickLabelWidth=0;
    Map<Double,TextLayout> axisLabelTextLayouts=new HashMap<Double,TextLayout>();
    for (int i=0; i < yAxis.getAxisTickCalculator().getTickLabels().size(); i++) {
      String tickLabel=yAxis.getAxisTickCalculator().getTickLabels().get(i);
      double tickLocation=yAxis.getAxisTickCalculator().getTickLocations().get(i);
      double flippedTickLocation=yOffset + height - tickLocation;
      if (tickLabel != null && flippedTickLocation > yOffset && flippedTickLocation < yOffset + height) {
        FontRenderContext frc=g.getFontRenderContext();
        TextLayout axisLabelTextLayout=new TextLayout(tickLabel,styler.getAxisTickLabelsFont(),frc);
        Rectangle2D tickLabelBounds=axisLabelTextLayout.getBounds();
        double boundWidth=tickLabelBounds.getWidth();
        if (boundWidth > maxTickLabelWidth) {
          maxTickLabelWidth=boundWidth;
        }
        axisLabelTextLayouts.put(tickLocation,axisLabelTextLayout);
      }
    }
    for (    Double tickLocation : axisLabelTextLayouts.keySet()) {
      TextLayout axisLabelTextLayout=axisLabelTextLayouts.get(tickLocation);
      Shape shape=axisLabelTextLayout.getOutline(null);
      Rectangle2D tickLabelBounds=shape.getBounds();
      double flippedTickLocation=yOffset + height - tickLocation;
      AffineTransform orig=g.getTransform();
      AffineTransform at=new AffineTransform();
      double boundWidth=tickLabelBounds.getWidth();
      double xPos;
switch (styler.getYAxisLabelAlignment()) {
case Right:
        xPos=xOffset + maxTickLabelWidth - boundWidth;
      break;
case Centre:
    xPos=xOffset + (maxTickLabelWidth - boundWidth) / 2;
  break;
case Left:
default :
xPos=xOffset;
}
at.translate(xPos,flippedTickLocation + tickLabelBounds.getHeight() / 2.0);
g.transform(at);
g.fill(shape);
g.setTransform(orig);
}
bounds=new Rectangle2D.Double(xOffset,yOffset,maxTickLabelWidth,height);
}
 else if (direction == Axis.Direction.X && styler.isXAxisTicksVisible()) {
double xOffset=chart.getXAxis().getBounds().getX();
double yOffset=chart.getXAxis().getAxisTitle().getBounds().getY();
double width=chart.getXAxis().getBounds().getWidth();
double maxTickLabelHeight=0;
int maxTickLabelY=0;
for (int i=0; i < chart.getXAxis().getAxisTickCalculator().getTickLabels().size(); i++) {
String tickLabel=chart.getXAxis().getAxisTickCalculator().getTickLabels().get(i);
double tickLocation=chart.getXAxis().getAxisTickCalculator().getTickLocations().get(i);
double shiftedTickLocation=xOffset + tickLocation;
if (tickLabel != null && shiftedTickLocation > xOffset && shiftedTickLocation < xOffset + width) {
FontRenderContext frc=g.getFontRenderContext();
TextLayout textLayout=new TextLayout(tickLabel,styler.getAxisTickLabelsFont(),frc);
AffineTransform rot=AffineTransform.getRotateInstance(-1 * Math.toRadians(styler.getXAxisLabelRotation()),0,0);
Shape shape=textLayout.getOutline(rot);
Rectangle2D tickLabelBounds=shape.getBounds2D();
if (tickLabelBounds.getBounds().height > maxTickLabelY) {
maxTickLabelY=tickLabelBounds.getBounds().height;
}
}
}
for (int i=0; i < chart.getXAxis().getAxisTickCalculator().getTickLabels().size(); i++) {
String tickLabel=chart.getXAxis().getAxisTickCalculator().getTickLabels().get(i);
double tickLocation=chart.getXAxis().getAxisTickCalculator().getTickLocations().get(i);
double shiftedTickLocation=xOffset + tickLocation;
if (tickLabel != null && shiftedTickLocation > xOffset && shiftedTickLocation < xOffset + width) {
FontRenderContext frc=g.getFontRenderContext();
TextLayout textLayout=new TextLayout(tickLabel,styler.getAxisTickLabelsFont(),frc);
AffineTransform rot=AffineTransform.getRotateInstance(-1 * Math.toRadians(styler.getXAxisLabelRotation()),0,0);
Shape shape=textLayout.getOutline(rot);
Rectangle2D tickLabelBounds=shape.getBounds2D();
int tickLabelY=tickLabelBounds.getBounds().height;
int yAlignmentOffset;
switch (styler.getXAxisLabelAlignmentVertical()) {
case Right:
yAlignmentOffset=maxTickLabelY - tickLabelY;
break;
case Centre:
yAlignmentOffset=(maxTickLabelY - tickLabelY) / 2;
break;
case Left:
default :
yAlignmentOffset=0;
}
AffineTransform orig=g.getTransform();
AffineTransform at=new AffineTransform();
double xPos;
switch (styler.getXAxisLabelAlignment()) {
case Left:
xPos=shiftedTickLocation;
break;
case Right:
xPos=shiftedTickLocation - tickLabelBounds.getWidth();
break;
case Centre:
default :
xPos=shiftedTickLocation - tickLabelBounds.getWidth() / 2.0;
}
double shiftX=-1 * tickLabelBounds.getX() * Math.sin(Math.toRadians(styler.getXAxisLabelRotation()));
double shiftY=-1 * (tickLabelBounds.getY() + tickLabelBounds.getHeight() + yAlignmentOffset);
at.translate(xPos + shiftX,yOffset + shiftY);
g.transform(at);
g.fill(shape);
g.setTransform(orig);
if (tickLabelBounds.getHeight() > maxTickLabelHeight) {
maxTickLabelHeight=tickLabelBounds.getHeight();
}
}
}
bounds=new Rectangle2D.Double(xOffset,yOffset - maxTickLabelHeight,width,maxTickLabelHeight);
}
 else {
bounds=new Rectangle2D.Double();
}
}
