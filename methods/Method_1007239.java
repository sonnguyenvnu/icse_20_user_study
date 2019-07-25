void parse(String line) throws ParseException {
  Authorization acl=parseAuthLine(line);
  if (acl == null) {
    return;
  }
  if (m_parsingUsersSpecificSection) {
    if (!m_userAuthorizations.containsKey(m_currentUser)) {
      m_userAuthorizations.put(m_currentUser,new ArrayList<Authorization>());
    }
    List<Authorization> userAuths=m_userAuthorizations.get(m_currentUser);
    userAuths.add(acl);
  }
 else   if (m_parsingPatternSpecificSection) {
    m_patternAuthorizations.add(acl);
  }
 else {
    m_globalAuthorizations.add(acl);
  }
}
