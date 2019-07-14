/** 
 * Returns a value in a score system compliant with the CSS Specificity rules.
 * @see <a href="https://www.w3.org/TR/CSS2/cascade.html">CSS Cascading</a>The score works as follows: <ul> <li> Id match adds 0x40000000 to the score. <li> Each class and voice match adds 4 to the score. <li> Tag matching adds 2 to the score. <li> Universal selector matching scores 1. </ul>
 * @param id The id of the cue if present, {@code null} otherwise.
 * @param tag Name of the tag, {@code null} if it refers to the entire cue.
 * @param classes An array containing the classes the tag belongs to. Must not be null.
 * @param voice Annotated voice if present, {@code null} otherwise.
 * @return The score of the match, zero if there is no match.
 */
public int getSpecificityScore(String id,String tag,String[] classes,String voice){
  if (targetId.isEmpty() && targetTag.isEmpty() && targetClasses.isEmpty() && targetVoice.isEmpty()) {
    return tag.isEmpty() ? 1 : 0;
  }
  int score=0;
  score=updateScoreForMatch(score,targetId,id,0x40000000);
  score=updateScoreForMatch(score,targetTag,tag,2);
  score=updateScoreForMatch(score,targetVoice,voice,4);
  if (score == -1 || !Arrays.asList(classes).containsAll(targetClasses)) {
    return 0;
  }
 else {
    score+=targetClasses.size() * 4;
  }
  return score;
}
