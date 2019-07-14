/** 
 * Split a large numeric value into a year/month/date values.
 * @param dateStr full date string
 * @param state parser state
 * @param val numeric value to use
 * @throws CalendarParserException if there was a problem splitting the value
 */
private static final void parseNumericBlob(String dateStr,ParserState state,int val) throws CalendarParserException {
  if (state.isYearSet() || state.isMonthSet() || state.isDateSet()) {
    throw new CalendarParserException("Unknown value " + val + " in date \"" + dateStr + "\"");
  }
  int tmpVal=val;
  if (state.isYearBeforeMonth()) {
    if (state.isYearBeforeDay()) {
      final int last=tmpVal % 100;
      tmpVal/=100;
      final int middle=tmpVal % 100;
      tmpVal/=100;
      state.setYear(tmpVal);
      if (state.isMonthBeforeDay()) {
        state.setMonth(middle);
        state.setDate(last);
      }
 else {
        state.setDate(middle);
        state.setMonth(last);
      }
    }
 else {
      state.setMonth(tmpVal % 100);
      tmpVal/=100;
      state.setYear(tmpVal % 10000);
      tmpVal/=10000;
      state.setDate(tmpVal);
    }
  }
 else   if (state.isYearBeforeDay()) {
    state.setDate(tmpVal % 100);
    tmpVal/=100;
    state.setYear(tmpVal % 10000);
    tmpVal/=10000;
    state.setMonth(tmpVal);
  }
 else {
    state.setYear(tmpVal % 10000);
    tmpVal/=10000;
    final int middle=tmpVal % 100;
    tmpVal/=100;
    if (state.isMonthBeforeDay()) {
      state.setDate(middle);
      state.setMonth(tmpVal);
    }
 else {
      state.setDate(tmpVal);
      state.setMonth(middle);
    }
  }
  if (DEBUG) {
    System.err.println("YEAR=" + state.getYear() + " MONTH=" + state.getMonth() + " DAY=" + state.getDate() + " (" + val + ") blob");
  }
}
