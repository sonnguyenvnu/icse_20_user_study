public MysqlForeignKey parseForeignKey(){
  accept(Token.FOREIGN);
  accept(Token.KEY);
  MysqlForeignKey fk=new MysqlForeignKey();
  if (lexer.token() != Token.LPAREN) {
    SQLName indexName=name();
    fk.setIndexName(indexName);
  }
  accept(Token.LPAREN);
  this.names(fk.getReferencingColumns(),fk);
  accept(Token.RPAREN);
  accept(Token.REFERENCES);
  fk.setReferencedTableName(this.name());
  accept(Token.LPAREN);
  this.names(fk.getReferencedColumns());
  accept(Token.RPAREN);
  if (lexer.identifierEquals(FnvHash.Constants.MATCH)) {
    lexer.nextToken();
    if (lexer.identifierEquals("FULL") || lexer.token() == Token.FULL) {
      fk.setReferenceMatch(SQLForeignKeyImpl.Match.FULL);
      lexer.nextToken();
    }
 else     if (lexer.identifierEquals(FnvHash.Constants.PARTIAL)) {
      fk.setReferenceMatch(SQLForeignKeyImpl.Match.PARTIAL);
      lexer.nextToken();
    }
 else     if (lexer.identifierEquals(FnvHash.Constants.SIMPLE)) {
      fk.setReferenceMatch(SQLForeignKeyImpl.Match.SIMPLE);
      lexer.nextToken();
    }
 else {
      throw new ParserException("TODO : " + lexer.info());
    }
  }
  while (lexer.token() == Token.ON) {
    lexer.nextToken();
    if (lexer.token() == Token.DELETE) {
      lexer.nextToken();
      SQLForeignKeyImpl.Option option=parseReferenceOption();
      fk.setOnDelete(option);
    }
 else     if (lexer.token() == Token.UPDATE) {
      lexer.nextToken();
      SQLForeignKeyImpl.Option option=parseReferenceOption();
      fk.setOnUpdate(option);
    }
 else {
      throw new ParserException("syntax error, expect DELETE or UPDATE, actual " + lexer.token() + " " + lexer.info());
    }
  }
  return fk;
}
