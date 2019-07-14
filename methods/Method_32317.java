/** 
 * Returns a new formatter that will use the specified chronology in preference to that of the printed object, or ISO on a parse. <p> When printing, this chronology will be used in preference to the chronology from the datetime that would otherwise be used. <p> When parsing, this chronology will be set on the parsed datetime. <p> A null chronology means no-override. If both an override chronology and an override zone are set, the override zone will take precedence over the zone in the chronology.
 * @param chrono  the chronology to use as an override
 * @return the new formatter
 */
public DateTimeFormatter withChronology(Chronology chrono){
  if (iChrono == chrono) {
    return this;
  }
  return new DateTimeFormatter(iPrinter,iParser,iLocale,iOffsetParsed,chrono,iZone,iPivotYear,iDefaultYear);
}
