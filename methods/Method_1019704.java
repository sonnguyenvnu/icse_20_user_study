/** 
 * Insert the backgroundImage in the given OutputStream.
 * @param out the pdf as a ByteArrayOutputStream
 */
public ByteArrayOutputStream insert(ByteArrayOutputStream out){
  try {
    Image image=Image.getInstance(imageBytes);
    float imageWidth=image.getWidth() * DEFAULT_DPI / image.getDpiX();
    float imageHeight=image.getHeight() * DEFAULT_DPI / image.getDpiY();
switch (repeat) {
case BOTH:
      ByteArrayOutputStream stream=out;
    for (float x=leftMargin; x < pageWidth - rightMargin; x+=imageWidth) {
      for (float y=pageHeight - topMargin; y > bottomMargin; y-=imageHeight) {
        if (x + imageWidth > pageWidth - rightMargin || y - imageHeight < bottomMargin) {
          byte[] data=new byte[(int)imageWidth * (int)imageHeight];
          for (int k=0; k < (int)imageHeight; k++) {
            for (int i=0; i < imageWidth; i++) {
              if (x + i < pageWidth - rightMargin && y - k > bottomMargin) {
                data[i + k * (int)imageWidth]=(byte)0xff;
              }
            }
          }
          Image clone=Image.getInstance(image);
          Image mask=Image.getInstance((int)imageWidth,(int)imageHeight,1,8,data);
          mask.makeMask();
          clone.setImageMask(mask);
          clone.setAbsolutePosition(x,y - imageHeight);
          stream=insertImage(stream,clone);
        }
 else {
          image.setAbsolutePosition(x,y - imageHeight);
          image.scaleAbsolute(imageWidth,imageHeight);
          stream=insertImage(stream,image);
        }
      }
    }
  return stream;
case NONE:
float y;
if (position.name().split("_")[0].equals("TOP")) {
y=pageHeight - imageHeight - topMargin;
}
 else if (position.name().split("_")[0].equals("CENTER")) {
y=(pageHeight - imageHeight - topMargin) / 2;
}
 else if (position.name().split("_")[0].equals("BOTTOM")) {
y=bottomMargin;
}
 else {
throw new UnsupportedOperationException(position + " is not supported");
}
float x;
if (position.name().split("_")[1].equals("LEFT")) {
x=leftMargin;
}
 else if (position.name().split("_")[1].equals("CENTER")) {
x=(pageWidth - imageWidth - rightMargin) / 2;
}
 else if (position.name().split("_")[1].equals("RIGHT")) {
x=pageWidth - imageWidth - rightMargin;
}
 else {
throw new UnsupportedOperationException(position + " is not supported");
}
image.setAbsolutePosition(x,y);
image.scaleAbsolute(imageWidth,imageHeight);
return insertImage(out,image);
case STRETCH:
image.setAbsolutePosition(leftMargin,bottomMargin);
image.scaleAbsolute(pageWidth - leftMargin - rightMargin,pageHeight - topMargin - bottomMargin);
return insertImage(out,image);
default :
throw new UnsupportedOperationException(repeat + " is not implemented");
}
}
 catch (Exception e) {
throw new RuntimeException(e);
}
}
