void parseSql(final String sqlString){
  rootNP=null;
  final int stringLength=sqlString.length();
  final StringBuilder pureSql=new StringBuilder(stringLength);
  boolean inQuote=false;
  int index=0;
  int paramCount=0;
  while (index < stringLength) {
    char c=sqlString.charAt(index);
    if (inQuote) {
      if (c == '\'') {
        inQuote=false;
      }
    }
 else     if (c == '\'') {
      inQuote=true;
    }
 else     if (c == ':' && index + 1 < stringLength && sqlString.charAt(index + 1) == ':') {
      int right=StringUtil.indexOfChars(sqlString,SQL_SEPARATORS,index + 2);
      if (right < 0) {
        right=stringLength;
      }
      pureSql.append(sqlString.substring(index,right));
      index=right;
      continue;
    }
 else     if (c == ':') {
      int right=StringUtil.indexOfChars(sqlString,SQL_SEPARATORS,index + 1);
      boolean batch=false;
      if (right < 0) {
        right=stringLength;
      }
 else {
        if (sqlString.charAt(right) == '!') {
          batch=true;
        }
      }
      String param=sqlString.substring(index + 1,right);
      if (!batch) {
        paramCount++;
        storeNamedParameter(param,paramCount);
        pureSql.append('?');
      }
 else {
        right++;
        int numStart=right;
        while (right < stringLength) {
          if (!CharUtil.isDigit(sqlString.charAt(right))) {
            break;
          }
          right++;
        }
        String numberValue=sqlString.substring(numStart,right);
        int batchSize;
        try {
          batchSize=Integer.parseInt(numberValue);
        }
 catch (        NumberFormatException nfex) {
          throw new DbSqlException("Batch size is not an integer: " + numberValue,nfex);
        }
        saveBatchParameter(param,batchSize);
        for (int i=1; i <= batchSize; i++) {
          if (i != 1) {
            pureSql.append(',');
          }
          paramCount++;
          storeNamedParameter(param + '.' + i,paramCount);
          pureSql.append('?');
        }
      }
      index=right;
      continue;
    }
 else     if (c == '?') {
      if ((index < stringLength - 1) && (Character.isDigit(sqlString.charAt(index + 1)))) {
        int right=StringUtil.indexOfChars(sqlString,SQL_SEPARATORS,index + 1);
        if (right < 0) {
          right=stringLength;
        }
        String param=sqlString.substring(index + 1,right);
        try {
          Integer.parseInt(param);
        }
 catch (        NumberFormatException nfex) {
          throw new DbSqlException("Positional parameter is not an integer: " + param,nfex);
        }
        paramCount++;
        storeNamedParameter(param,paramCount);
        pureSql.append('?');
        index=right;
        continue;
      }
      paramCount++;
    }
    pureSql.append(c);
    index++;
  }
  this.prepared=(paramCount != 0);
  this.sql=pureSql.toString();
  if (this.sql.startsWith("{")) {
    this.callable=true;
  }
}
