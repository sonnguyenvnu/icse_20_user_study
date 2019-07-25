@Override public void write(List<? extends PlayerSummary> summaries){
  for (  PlayerSummary summary : summaries) {
    MapSqlParameterSource args=new MapSqlParameterSource().addValue("id",summary.getId()).addValue("year",summary.getYear()).addValue("completes",summary.getCompletes()).addValue("attempts",summary.getAttempts()).addValue("passingYards",summary.getPassingYards()).addValue("passingTd",summary.getPassingTd()).addValue("interceptions",summary.getInterceptions()).addValue("rushes",summary.getRushes()).addValue("rushYards",summary.getRushYards()).addValue("receptions",summary.getReceptions()).addValue("receptionYards",summary.getReceptionYards()).addValue("totalTd",summary.getTotalTd());
    namedParameterJdbcTemplate.update(INSERT_SUMMARY,args);
  }
}
