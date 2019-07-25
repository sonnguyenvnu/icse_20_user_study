@Override void analyze(Locals locals){
  if (!read) {
    throw createError(new IllegalArgumentException("Must read from constant [" + value + "]."));
  }
  if (value.endsWith("f") || value.endsWith("F")) {
    try {
      constant=Float.parseFloat(value.substring(0,value.length() - 1));
      actual=locals.getDefinition().floatType;
    }
 catch (    NumberFormatException exception) {
      throw createError(new IllegalArgumentException("Invalid float constant [" + value + "]."));
    }
  }
 else {
    String toParse=value;
    if (toParse.endsWith("d") || value.endsWith("D")) {
      toParse=toParse.substring(0,value.length() - 1);
    }
    try {
      constant=Double.parseDouble(toParse);
      actual=locals.getDefinition().doubleType;
    }
 catch (    NumberFormatException exception) {
      throw createError(new IllegalArgumentException("Invalid double constant [" + value + "]."));
    }
  }
}
