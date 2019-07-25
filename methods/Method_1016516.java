public static String setter(Property property){
  if (property.isUsingBeanConvention()) {
    return "set" + property.getCapitalizedName();
  }
 else {
    return property.getName();
  }
}
