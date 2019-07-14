/** 
 * Returns a new formatter that will create a datetime with a time zone equal to that of the offset of the parsed string. <p> After calling this method, a string '2004-06-09T10:20:30-08:00' will create a datetime with a zone of -08:00 (a fixed zone, with no daylight savings rules). If the parsed string represents a local time (no zone offset) the parsed datetime will be in the default zone. <p> Calling this method sets the override zone to null. Calling the override zone method sets this flag off.
 * @return the new formatter
 */
public DateTimeFormatter withOffsetParsed(){
  if (iOffsetParsed == true) {
    return this;
  }
  return new DateTimeFormatter(iPrinter,iParser,iLocale,true,iChrono,null,iPivotYear,iDefaultYear);
}
