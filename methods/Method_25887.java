private static ValidationResult validate(String formatString,Iterable<Object> arguments){
  try {
    String unused=String.format(formatString,Iterables.toArray(arguments,Object.class));
  }
 catch (  DuplicateFormatFlagsException e) {
    return ValidationResult.create(e,String.format("duplicate format flags: %s",e.getFlags()));
  }
catch (  FormatFlagsConversionMismatchException e) {
    return ValidationResult.create(e,String.format("format specifier '%%%s' is not compatible with the given flag(s): %s",e.getConversion(),e.getFlags()));
  }
catch (  IllegalFormatCodePointException e) {
    return ValidationResult.create(e,String.format("invalid Unicode code point: %x",e.getCodePoint()));
  }
catch (  IllegalFormatConversionException e) {
    return ValidationResult.create(e,String.format("illegal format conversion: '%s' cannot be formatted using '%%%s'",e.getArgumentClass().getName(),e.getConversion()));
  }
catch (  IllegalFormatFlagsException e) {
    return ValidationResult.create(e,String.format("illegal format flags: %s",e.getFlags()));
  }
catch (  IllegalFormatPrecisionException e) {
    return ValidationResult.create(e,String.format("illegal format precision: %d",e.getPrecision()));
  }
catch (  IllegalFormatWidthException e) {
    return ValidationResult.create(e,String.format("illegal format width: %s",e.getWidth()));
  }
catch (  MissingFormatArgumentException e) {
    return ValidationResult.create(e,String.format("missing argument for format specifier '%s'",e.getFormatSpecifier()));
  }
catch (  MissingFormatWidthException e) {
    return ValidationResult.create(e,String.format("missing format width: %s",e.getFormatSpecifier()));
  }
catch (  UnknownFormatConversionException e) {
    return ValidationResult.create(e,unknownFormatConversion(e.getConversion()));
  }
catch (  UnknownFormatFlagsException e) {
    return ValidationResult.create(e,String.format("unknown format flag(s): %s",e.getFlags()));
  }
  try {
    String[] argDescriptors=Collections.nCopies(Iterables.size(arguments),"Ljava/lang/Object;").toArray(new String[0]);
    Formatter.check(formatString,argDescriptors);
  }
 catch (  ExtraFormatArgumentsException e) {
    return ValidationResult.create(e,String.format("extra format arguments: used %d, provided %d",e.used,e.provided));
  }
catch (  Exception ignored) {
  }
  return null;
}
