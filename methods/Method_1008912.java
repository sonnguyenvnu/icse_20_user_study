public void debug(String propertyName,CSSValue value){
  short valueType=value.getCssValueType();
switch (valueType) {
case CSSValue.CSS_CUSTOM:
    log.warn("valueType CUSTOM for " + propertyName + " - NOT IMPLEMENTED ");
  break;
case CSSValue.CSS_INHERIT:
log.warn("valueType INHERIT for " + propertyName + " - NOT IMPLEMENTED ");
break;
case CSSValue.CSS_PRIMITIVE_VALUE:
log.debug("valueType PRIMITIVE for " + propertyName);
CSSPrimitiveValue cssPrimitiveValue=(CSSPrimitiveValue)value;
log.debug("PrimitiveType: " + cssPrimitiveValue.getPrimitiveType());
break;
case CSSValue.CSS_VALUE_LIST:
log.debug("valueType LIST for " + propertyName);
CSSValueList cssValueList=(CSSValueList)value;
break;
default :
log.error("Unexpected valueType " + valueType + " for " + propertyName);
return;
}
}
