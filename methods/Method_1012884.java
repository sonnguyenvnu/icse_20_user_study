/** 
 * Returns a builder for  {@link FeedbackResponseAttributes}.
 */
public static Builder builder(String feedbackQuestionId,String giver,String recipient){
  return new Builder(feedbackQuestionId,giver,recipient);
}
