private String getTableName(Uri uri){
  String tableName=null;
switch (sUriMatcher.match(uri)) {
case BOOK_URI_CODE:
    tableName=DbOpenHelper.BOOK_TABLE_NAME;
  break;
case USER_URI_CODE:
tableName=DbOpenHelper.USER_TALBE_NAME;
break;
default :
break;
}
return tableName;
}
