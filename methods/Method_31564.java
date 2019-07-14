private void extractWarnings(Results results,Statement statement) throws SQLException {
  SQLWarning warning=statement.getWarnings();
  while (warning != null) {
    results.addWarning(new WarningImpl(warning.getErrorCode(),warning.getSQLState(),warning.getMessage()));
    warning=warning.getNextWarning();
  }
}
