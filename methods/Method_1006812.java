private void between(StringBuilder sb){
  sb.append(SqlScript.PLACE_HOLDER).append(Conjunction.AND.sql()).append(SqlScript.PLACE_HOLDER);
}
