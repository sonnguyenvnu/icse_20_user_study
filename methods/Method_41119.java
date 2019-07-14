protected void buildExpression(String expression) throws ParseException {
  expressionParsed=true;
  try {
    if (seconds == null) {
      seconds=new TreeSet<Integer>();
    }
    if (minutes == null) {
      minutes=new TreeSet<Integer>();
    }
    if (hours == null) {
      hours=new TreeSet<Integer>();
    }
    if (daysOfMonth == null) {
      daysOfMonth=new TreeSet<Integer>();
    }
    if (months == null) {
      months=new TreeSet<Integer>();
    }
    if (daysOfWeek == null) {
      daysOfWeek=new TreeSet<Integer>();
    }
    if (years == null) {
      years=new TreeSet<Integer>();
    }
    int exprOn=SECOND;
    StringTokenizer exprsTok=new StringTokenizer(expression," \t",false);
    while (exprsTok.hasMoreTokens() && exprOn <= YEAR) {
      String expr=exprsTok.nextToken().trim();
      if (exprOn == DAY_OF_MONTH && expr.indexOf('L') != -1 && expr.length() > 1 && expr.contains(",")) {
        throw new ParseException("Support for specifying 'L' and 'LW' with other days of the month is not implemented",-1);
      }
      if (exprOn == DAY_OF_WEEK && expr.indexOf('L') != -1 && expr.length() > 1 && expr.contains(",")) {
        throw new ParseException("Support for specifying 'L' with other days of the week is not implemented",-1);
      }
      if (exprOn == DAY_OF_WEEK && expr.indexOf('#') != -1 && expr.indexOf('#',expr.indexOf('#') + 1) != -1) {
        throw new ParseException("Support for specifying multiple \"nth\" days is not implemented.",-1);
      }
      StringTokenizer vTok=new StringTokenizer(expr,",");
      while (vTok.hasMoreTokens()) {
        String v=vTok.nextToken();
        storeExpressionVals(0,v,exprOn);
      }
      exprOn++;
    }
    if (exprOn <= DAY_OF_WEEK) {
      throw new ParseException("Unexpected end of expression.",expression.length());
    }
    if (exprOn <= YEAR) {
      storeExpressionVals(0,"*",YEAR);
    }
    TreeSet<Integer> dow=getSet(DAY_OF_WEEK);
    TreeSet<Integer> dom=getSet(DAY_OF_MONTH);
    boolean dayOfMSpec=!dom.contains(NO_SPEC);
    boolean dayOfWSpec=!dow.contains(NO_SPEC);
    if (!dayOfMSpec || dayOfWSpec) {
      if (!dayOfWSpec || dayOfMSpec) {
        throw new ParseException("Support for specifying both a day-of-week AND a day-of-month parameter is not implemented.",0);
      }
    }
  }
 catch (  ParseException pe) {
    throw pe;
  }
catch (  Exception e) {
    throw new ParseException("Illegal cron expression format (" + e.toString() + ")",0);
  }
}
