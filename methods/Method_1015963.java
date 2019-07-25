/** 
 * Get the options in a form that can be sent to clients.
 * @param includePassword Include the actual password with the information. This should only be sent to people in the game.
 * @return This game's general information: ID, host, state, player list, etc.
 */
public Map<GameOptionData,Object> serialize(final boolean includePassword){
  final Map<GameOptionData,Object> info=new HashMap<GameOptionData,Object>();
  info.put(GameOptionData.CARD_SETS,cardSetIds);
  info.put(GameOptionData.BLANKS_LIMIT,blanksInDeck);
  info.put(GameOptionData.PLAYER_LIMIT,playerLimit);
  info.put(GameOptionData.SPECTATOR_LIMIT,spectatorLimit);
  info.put(GameOptionData.SCORE_LIMIT,scoreGoal);
  info.put(GameOptionData.TIMER_MULTIPLIER,timerMultiplier);
  if (includePassword) {
    info.put(GameOptionData.PASSWORD,password);
  }
  return info;
}
