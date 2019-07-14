public static Easing getInterpolator(String configString){
  if (configString == null) {
    return null;
  }
  if (configString.startsWith("cubic")) {
    return new CubicEasing(configString);
  }
 else {
switch (configString) {
case STANDARD_NAME:
      return new CubicEasing(STANDARD);
case ACCELERATE_NAME:
    return new CubicEasing(ACCELERATE);
case DECELERATE_NAME:
  return new CubicEasing(DECELERATE);
case LINEAR_NAME:
return new CubicEasing(LINEAR);
default :
System.err.println("transitionEasing syntax error syntax:" + "transitionEasing=\"cubic(1.0,0.5,0.0,0.6)\" or " + Arrays.toString(NAMED_EASING) + " not " + configString);
}
}
return sDefault;
}
