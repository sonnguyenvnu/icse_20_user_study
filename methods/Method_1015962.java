/** 
 * Update the options in-place (so that the Game doesn't need more locks).
 * @param newOptions The new options to use.
 */
public void update(final GameOptions newOptions){
  this.scoreGoal=newOptions.scoreGoal;
  this.playerLimit=newOptions.playerLimit;
  this.spectatorLimit=newOptions.spectatorLimit;
synchronized (this.cardSetIds) {
    this.cardSetIds.clear();
    this.cardSetIds.addAll(newOptions.cardSetIds);
  }
  this.blanksInDeck=newOptions.blanksInDeck;
  this.password=newOptions.password;
  this.timerMultiplier=newOptions.timerMultiplier;
}
