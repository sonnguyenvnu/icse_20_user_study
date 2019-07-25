@Override public List<Object> apply(Object o2){
  if (o2 instanceof JAXBElement) {
    if (((JAXBElement)o2).getName().getLocalPart().equals("instrText")) {
      Text instr=(Text)XmlUtils.unwrap(o2);
      fieldsPresent.add(instr.getValue());
      System.out.println(instr.getValue());
    }
  }
  Object o=XmlUtils.unwrap(o2);
  if (o instanceof org.docx4j.vml.CTImageData) {
    ((CTImageData)o).setTitle("foo");
    if (((CTImageData)o).getRelid() != null) {
      String rId=((CTImageData)o).getRelid();
      Part embeddedPart=sourcePart.getRelationshipsPart().getPart(rId);
      if (embeddedPart instanceof ImagePngPart || embeddedPart instanceof ImageGifPart || embeddedPart instanceof ImageJpegPart || embeddedPart instanceof ImageBmpPart || embeddedPart instanceof ImageTiffPart) {
      }
 else {
      }
    }
  }
 else   if (o instanceof org.docx4j.math.CTOMathPara) {
    unsafeObjects.add(o.getClass().getName());
  }
  return null;
}
