/** 
 * The transaction has completed successfully. The participant previously informed the coordinator that it was ready to complete.
 * @throws WrongStateException never in this implementation.
 * @throws SystemException never in this implementation.
 */
public void close() throws WrongStateException, SystemException {
  System.out.println("[SERVICE] Participant.close (The participant knows that this BA is now finished and can throw away any temporary state)");
  removeParticipant(txID);
}
