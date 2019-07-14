/** 
 * Subscript safe .charAt()
 * @param at index of character to access
 * @return null if index out of bounds, .charAt() otherwise
 */
char CharAt(int at){
  if ((at < 0) || (at > (m_length - 1))) {
    return '\0';
  }
  return m_inWord.charAt(at);
}
