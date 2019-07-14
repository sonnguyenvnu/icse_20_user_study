private String stringForQuantity(int quantity){
switch (quantity) {
case QUANTITY_ZERO:
    return "zero";
case QUANTITY_ONE:
  return "one";
case QUANTITY_TWO:
return "two";
case QUANTITY_FEW:
return "few";
case QUANTITY_MANY:
return "many";
default :
return "other";
}
}
