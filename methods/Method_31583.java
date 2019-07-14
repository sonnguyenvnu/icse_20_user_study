/** 
 * Whether the current set of tokens should be discarded.
 * @param token              The latest token.
 * @param nonCommentPartSeen Whether a non-comment part has already be seen.
 * @return {@code true} if it should, {@code false} if not.
 */
protected boolean shouldDiscard(Token token,boolean nonCommentPartSeen){
  TokenType tokenType=token.getType();
  return (tokenType == TokenType.DELIMITER || tokenType == TokenType.BLANK_LINES) && !nonCommentPartSeen;
}
