/** 
 * Returns a generic ISO datetime parser which parses either a date or a time or both. <p> The returned formatter can only be used for parsing, printing is unsupported. <p> The parser is strict by default, thus time string  {@code 24:00} cannot be parsed.<p> It accepts formats described by the following syntax: <pre> datetime          = time | date-opt-time time              = 'T' time-element [offset] date-opt-time     = date-element ['T' [time-element] [offset]] date-element      = std-date-element | ord-date-element | week-date-element std-date-element  = yyyy ['-' MM ['-' dd]] ord-date-element  = yyyy ['-' DDD] week-date-element = xxxx '-W' ww ['-' e] time-element      = HH [minute-element] | [fraction] minute-element    = ':' mm [second-element] | [fraction] second-element    = ':' ss [fraction] fraction          = ('.' | ',') digit+ offset            = 'Z' | (('+' | '-') HH [':' mm [':' ss [('.' | ',') SSS]]]) </pre>
 */
public static DateTimeFormatter dateTimeParser(){
  return Constants.dtp;
}
