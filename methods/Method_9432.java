private TLRPC.TL_paymentRequestedInfo getRequestInfo(){
  TLRPC.TL_paymentRequestedInfo info=new TLRPC.TL_paymentRequestedInfo();
  if (paymentForm.invoice.name_requested) {
    info.name=inputFields[FIELD_NAME].getText().toString();
    info.flags|=1;
  }
  if (paymentForm.invoice.phone_requested) {
    info.phone="+" + inputFields[FIELD_PHONECODE].getText().toString() + inputFields[FIELD_PHONE].getText().toString();
    info.flags|=2;
  }
  if (paymentForm.invoice.email_requested) {
    info.email=inputFields[FIELD_EMAIL].getText().toString().trim();
    info.flags|=4;
  }
  if (paymentForm.invoice.shipping_address_requested) {
    info.shipping_address=new TLRPC.TL_postAddress();
    info.shipping_address.street_line1=inputFields[FIELD_STREET1].getText().toString();
    info.shipping_address.street_line2=inputFields[FIELD_STREET2].getText().toString();
    info.shipping_address.city=inputFields[FIELD_CITY].getText().toString();
    info.shipping_address.state=inputFields[FIELD_STATE].getText().toString();
    info.shipping_address.country_iso2=countryName != null ? countryName : "";
    info.shipping_address.post_code=inputFields[FIELD_POSTCODE].getText().toString();
    info.flags|=8;
  }
  return info;
}
