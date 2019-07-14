/** 
 * Use a numeric token from the date string.
 * @param dateStr full date string
 * @param state parser state
 * @param val numeric value to use
 * @throws CalendarParserException if there was a problem parsing the token
 */
private static final void parseNumericToken(String dateStr,ParserState state,int val) throws CalendarParserException {
  if (state.isYearSet() && state.isMonthSet() && state.isDateSet()) {
    if (DEBUG) {
      System.err.println("*** Extra number " + val);
    }
    throw new CalendarParserException("Extra value \"" + val + "\" in date \"" + dateStr + "\"");
  }
  if (val < 0) {
    if (DEBUG) {
      System.err.println("*** Negative number " + val);
    }
    throw new CalendarParserException("Found negative number in" + " date \"" + dateStr + "\"");
  }
  if (val > 9999) {
    parseNumericBlob(dateStr,state,val);
    return;
  }
  if (val > 31) {
    if (!state.isYearSet()) {
      state.setYear(val);
      if (DEBUG) {
        System.err.println("YEAR=" + state.getYear() + " (" + val + ") >31");
      }
      return;
    }
    if (state.getYear() > 31) {
      if (DEBUG) {
        System.err.println("*** Ambiguous year " + state.getYear() + " vs. " + val);
      }
      String errMsg="Couldn't decide on year number in date \"" + dateStr + "\"";
      throw new CalendarParserException(errMsg);
    }
    if (state.getYear() > 12) {
      if (!state.isDateSet()) {
        state.setDate(state.getYear());
        state.setYear(val);
        if (DEBUG) {
          System.err.println("YEAR=" + state.getYear() + ", DAY=" + state.getDate() + " (" + val + ") >31 swap");
        }
        return;
      }
      if (state.getDate() <= 12) {
        state.setMonth(state.getDate());
        state.setDate(state.getYear());
        state.setYear(val);
        if (DEBUG) {
          System.err.println("YEAR=" + state.getYear() + ", MONTH=" + state.getMonth() + ", DAY=" + state.getDate() + " (" + val + ") >31 megaswap");
        }
        return;
      }
      if (DEBUG) {
        System.err.println("*** Unassignable year-like" + " number " + val);
      }
      throw new CalendarParserException("Bad number " + val + " found in date \"" + dateStr + "\"");
    }
    if (!state.isDateSet() && !state.isMonthSet()) {
      if (state.isMonthBeforeDay()) {
        state.setMonth(state.getYear());
        state.setYear(val);
        if (DEBUG) {
          System.err.println("YEAR=" + state.getYear() + ", MONTH=" + state.getMonth() + " (" + val + ") >31 swap");
        }
      }
 else {
        state.setDate(state.getYear());
        state.setYear(val);
        if (DEBUG) {
          System.err.println("YEAR=" + state.getYear() + ", DAY=" + state.getDate() + " (" + val + ") >31 swap#2");
        }
      }
      return;
    }
    if (!state.isDateSet()) {
      state.setDate(state.getYear());
      state.setYear(val);
      if (DEBUG) {
        System.err.println("YEAR=" + state.getYear() + ", DAY=" + state.getDate() + " (" + val + ") >31 day swap");
      }
      return;
    }
    state.setMonth(state.getYear());
    state.setYear(val);
    if (DEBUG) {
      System.err.println("YEAR=" + state.getYear() + ", MONTH=" + state.getMonth() + " (" + val + ") >31 mon swap");
    }
    return;
  }
  if (val > 12) {
    if (!state.isYearSet()) {
      if (state.isDateSet() || state.isYearBeforeDay()) {
        state.setYear(val);
        if (DEBUG) {
          System.err.println("YEAR=" + state.getYear() + " (" + val + ") >12");
        }
      }
 else {
        state.setDate(val);
        if (DEBUG) {
          System.err.println("DAY=" + state.getDate() + " (" + val + ") >12");
        }
      }
      return;
    }
    if (!state.isDateSet()) {
      state.setDate(val);
      if (DEBUG) {
        System.err.println("DAY=" + state.getDate() + " (" + val + ") >12 !yr");
      }
      return;
    }
    if (DEBUG) {
      System.err.println("*** Unassignable year/day number " + val);
    }
    throw new CalendarParserException("Bad number " + val + " found in date \"" + dateStr + "\"");
  }
  if (state.isYearSet()) {
    if (state.isMonthSet() || (!state.isDateSet() && !state.isMonthBeforeDay())) {
      state.setDate(val);
      if (DEBUG) {
        System.err.println("DAY=" + state.getDate() + " (" + val + ") ambig!yr");
      }
    }
 else {
      state.setMonth(val);
      if (DEBUG) {
        System.err.println("MONTH=" + state.getMonth() + " (" + val + ") ambig!yr");
      }
    }
    return;
  }
  if (state.isMonthSet()) {
    if (state.isDateSet() || state.isYearBeforeDay()) {
      state.setYear(val);
      if (DEBUG) {
        System.err.println("YEAR=" + state.getYear() + " (" + val + ") ambig!mo");
      }
    }
 else {
      state.setDate(val);
      if (DEBUG) {
        System.err.println("DAY=" + state.getDate() + " (" + val + ") ambig!mo");
      }
    }
    return;
  }
  if (state.isDateSet()) {
    if (state.isYearBeforeMonth()) {
      state.setYear(val);
      if (DEBUG) {
        System.err.println("YEAR=" + state.getYear() + " (" + val + ") ambig!day");
      }
    }
 else {
      state.setMonth(val);
      if (DEBUG) {
        System.err.println("MONTH=" + state.getMonth() + " (" + val + ") ambig!day");
      }
    }
    return;
  }
  if (state.isYearBeforeMonth()) {
    if (state.isYearBeforeDay()) {
      state.setYear(val);
      if (DEBUG) {
        System.err.println("YEAR=" + state.getYear() + " (" + val + ") YM|YD");
      }
    }
 else {
      state.setDate(val);
      if (DEBUG) {
        System.err.println("DAY=" + state.getDate() + " (" + val + ") YM!YD");
      }
    }
  }
 else   if (state.isMonthBeforeDay()) {
    state.setMonth(val);
    if (DEBUG) {
      System.err.println("MONTH=" + state.getMonth() + " (" + val + ") !YM|MD");
    }
  }
 else {
    state.setDate(val);
    if (DEBUG) {
      System.err.println("DAY=" + state.getDate() + " (" + val + ") !YM!MD");
    }
  }
}
