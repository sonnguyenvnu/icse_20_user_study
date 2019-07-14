private static String[] retrieveVariants(ResourceBundle b){
  return new String[]{b.getString("PeriodFormat.space"),b.getString("PeriodFormat.comma"),b.getString("PeriodFormat.commandand"),b.getString("PeriodFormat.commaspaceand")};
}
