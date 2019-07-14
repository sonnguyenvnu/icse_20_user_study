public final Object getElementValue(Context ctx,Node element,boolean trim){
  String text=element.getTextContent();
  return getValue(ctx,trim ? text.trim() : text);
}
