/** 
 * Adds a race result.
 * @param raceResult
 * @return
 */
public ChampionshipStandings add(RaceResult raceResult){
  processRacerResult(raceResult.getRacer1Name(),raceResult.getRacer1Position(),racerPointsTotals);
  processRacerResult(raceResult.getRacer2Name(),raceResult.getRacer2Position(),racerPointsTotals);
  processRacerResult(raceResult.getRacer3Name(),raceResult.getRacer3Position(),racerPointsTotals);
  processRacerResult(raceResult.getRacer4Name(),raceResult.getRacer4Position(),racerPointsTotals);
  return this;
}
