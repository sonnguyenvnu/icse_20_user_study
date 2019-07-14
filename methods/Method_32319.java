/** 
 * Returns a new formatter that will use the specified zone in preference to the zone of the printed object, or default zone on a parse. <p> When printing, this zone will be used in preference to the zone from the datetime that would otherwise be used. <p> When parsing, this zone will be set on the parsed datetime. <p> A null zone means of no-override. If both an override chronology and an override zone are set, the override zone will take precedence over the zone in the chronology.
 * @param zone  the zone to use as an override
 * @return the new formatter
 */
public DateTimeFormatter withZone(DateTimeZone zone){
  if (iZone == zone) {
    return this;
  }
  return new DateTimeFormatter(iPrinter,iParser,iLocale,false,iChrono,zone,iPivotYear,iDefaultYear);
}
