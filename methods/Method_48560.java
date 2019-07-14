private String createQueryString(IndexQueryBuilder query,final ElementCategory resultType,final StandardJanusGraphTx transaction,MixedIndexType index){
  Preconditions.checkArgument(index.getElement() == resultType,"Index is not configured for the desired result type: %s",resultType);
  final String backingIndexName=index.getBackingIndexName();
  final IndexProvider indexInformation=(IndexProvider)mixedIndexes.get(backingIndexName);
  final StringBuilder qB=new StringBuilder(query.getQuery());
  final String prefix=query.getPrefix();
  Preconditions.checkNotNull(prefix);
  int replacements=0;
  int pos=0;
  while (pos < qB.length()) {
    pos=qB.indexOf(prefix,pos);
    if (pos < 0)     break;
    final int startPos=pos;
    pos+=prefix.length();
    final StringBuilder keyBuilder=new StringBuilder();
    final boolean quoteTerminated=qB.charAt(pos) == '"';
    if (quoteTerminated)     pos++;
    while (pos < qB.length() && (Character.isLetterOrDigit(qB.charAt(pos)) || (quoteTerminated && qB.charAt(pos) != '"') || qB.charAt(pos) == '*')) {
      keyBuilder.append(qB.charAt(pos));
      pos++;
    }
    if (quoteTerminated)     pos++;
    final int endPos=pos;
    final String keyName=keyBuilder.toString();
    Preconditions.checkArgument(StringUtils.isNotBlank(keyName),"Found reference to empty key at position [%s]",startPos);
    String replacement;
    if (keyName.equals("*")) {
      replacement=indexInformation.getFeatures().getWildcardField();
    }
 else     if (transaction.containsRelationType(keyName)) {
      final PropertyKey key=transaction.getPropertyKey(keyName);
      Preconditions.checkNotNull(key);
      Preconditions.checkArgument(index.indexesKey(key),"The used key [%s] is not indexed in the targeted index [%s]",key.name(),query.getIndex());
      replacement=key2Field(index,key);
    }
 else {
      Preconditions.checkArgument(query.getUnknownKeyName() != null,"Found reference to non-existant property key in query at position [%s]: %s",startPos,keyName);
      replacement=query.getUnknownKeyName();
    }
    Preconditions.checkArgument(StringUtils.isNotBlank(replacement));
    qB.replace(startPos,endPos,replacement);
    pos=startPos + replacement.length();
    replacements++;
  }
  final String queryStr=qB.toString();
  if (replacements <= 0)   log.warn("Could not convert given {} index query: [{}]",resultType,query.getQuery());
  log.info("Converted query string with {} replacements: [{}] => [{}]",replacements,query.getQuery(),queryStr);
  return queryStr;
}
