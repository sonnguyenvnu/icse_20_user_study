/** 
 * Check whether the current guess is correct, and update the biggest/smallest guesses as needed. Give feedback to the user if they are correct.
 */
public void check(){
  if (guess > number) {
    biggest=guess - 1;
  }
 else   if (guess < number) {
    smallest=guess + 1;
  }
 else   if (guess == number) {
    FacesContext.getCurrentInstance().addMessage(null,new FacesMessage("Correct!"));
  }
  remainingGuesses--;
}
