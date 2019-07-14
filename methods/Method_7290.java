/** 
 * <p>Gets an appropriate rule for the padding required.</p>
 * @param field   the field to get a rule for
 * @param padding the padding required
 * @return a new rule with the correct padding
 */
protected NumberRule selectNumberRule(final int field,final int padding){
switch (padding) {
case 1:
    return new UnpaddedNumberField(field);
case 2:
  return new TwoDigitNumberField(field);
default :
return new PaddedNumberField(field,padding);
}
}
