/** 
 * Verify if the 2 topics matching respecting the rules of MQTT Appendix A
 * @param subscriptionTopic the topic filter of the subscription
 * @return true if the two topics match.
 */
public boolean match(Topic subscriptionTopic){
  List<Token> msgTokens=getTokens();
  List<Token> subscriptionTokens=subscriptionTopic.getTokens();
  int i=0;
  for (; i < subscriptionTokens.size(); i++) {
    Token subToken=subscriptionTokens.get(i);
    if (!Token.MULTI.equals(subToken) && !Token.SINGLE.equals(subToken)) {
      if (i >= msgTokens.size()) {
        return false;
      }
      Token msgToken=msgTokens.get(i);
      if (!msgToken.equals(subToken)) {
        return false;
      }
    }
 else {
      if (Token.MULTI.equals(subToken)) {
        return true;
      }
    }
  }
  return i == msgTokens.size();
}
