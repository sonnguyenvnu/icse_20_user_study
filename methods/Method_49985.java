/** 
 * Set octet value to pdu header by header field.
 * @param value the value
 * @param field the field
 * @throws InvalidHeaderValueException if the value is invalid.
 */
protected void setOctet(int value,int field) throws InvalidHeaderValueException {
switch (field) {
case REPORT_ALLOWED:
case ADAPTATION_ALLOWED:
case DELIVERY_REPORT:
case DRM_CONTENT:
case DISTRIBUTION_INDICATOR:
case QUOTAS:
case READ_REPORT:
case STORE:
case STORED:
case TOTALS:
case SENDER_VISIBILITY:
    if ((VALUE_YES != value) && (VALUE_NO != value)) {
      throw new InvalidHeaderValueException("Invalid Octet value!");
    }
  break;
case READ_STATUS:
if ((READ_STATUS_READ != value) && (READ_STATUS__DELETED_WITHOUT_BEING_READ != value)) {
  throw new InvalidHeaderValueException("Invalid Octet value!");
}
break;
case CANCEL_STATUS:
if ((CANCEL_STATUS_REQUEST_SUCCESSFULLY_RECEIVED != value) && (CANCEL_STATUS_REQUEST_CORRUPTED != value)) {
throw new InvalidHeaderValueException("Invalid Octet value!");
}
break;
case PRIORITY:
if ((value < PRIORITY_LOW) || (value > PRIORITY_HIGH)) {
throw new InvalidHeaderValueException("Invalid Octet value!");
}
break;
case STATUS:
if ((value < STATUS_EXPIRED) || (value > STATUS_UNREACHABLE)) {
throw new InvalidHeaderValueException("Invalid Octet value!");
}
break;
case REPLY_CHARGING:
if ((value < REPLY_CHARGING_REQUESTED) || (value > REPLY_CHARGING_ACCEPTED_TEXT_ONLY)) {
throw new InvalidHeaderValueException("Invalid Octet value!");
}
break;
case MM_STATE:
if ((value < MM_STATE_DRAFT) || (value > MM_STATE_FORWARDED)) {
throw new InvalidHeaderValueException("Invalid Octet value!");
}
break;
case RECOMMENDED_RETRIEVAL_MODE:
if (RECOMMENDED_RETRIEVAL_MODE_MANUAL != value) {
throw new InvalidHeaderValueException("Invalid Octet value!");
}
break;
case CONTENT_CLASS:
if ((value < CONTENT_CLASS_TEXT) || (value > CONTENT_CLASS_CONTENT_RICH)) {
throw new InvalidHeaderValueException("Invalid Octet value!");
}
break;
case RETRIEVE_STATUS:
if ((value > RETRIEVE_STATUS_ERROR_TRANSIENT_NETWORK_PROBLEM) && (value < RETRIEVE_STATUS_ERROR_PERMANENT_FAILURE)) {
value=RETRIEVE_STATUS_ERROR_TRANSIENT_FAILURE;
}
 else if ((value > RETRIEVE_STATUS_ERROR_PERMANENT_CONTENT_UNSUPPORTED) && (value <= RETRIEVE_STATUS_ERROR_END)) {
value=RETRIEVE_STATUS_ERROR_PERMANENT_FAILURE;
}
 else if ((value < RETRIEVE_STATUS_OK) || ((value > RETRIEVE_STATUS_OK) && (value < RETRIEVE_STATUS_ERROR_TRANSIENT_FAILURE)) || (value > RETRIEVE_STATUS_ERROR_END)) {
value=RETRIEVE_STATUS_ERROR_PERMANENT_FAILURE;
}
break;
case STORE_STATUS:
if ((value > STORE_STATUS_ERROR_TRANSIENT_NETWORK_PROBLEM) && (value < STORE_STATUS_ERROR_PERMANENT_FAILURE)) {
value=STORE_STATUS_ERROR_TRANSIENT_FAILURE;
}
 else if ((value > STORE_STATUS_ERROR_PERMANENT_MMBOX_FULL) && (value <= STORE_STATUS_ERROR_END)) {
value=STORE_STATUS_ERROR_PERMANENT_FAILURE;
}
 else if ((value < STORE_STATUS_SUCCESS) || ((value > STORE_STATUS_SUCCESS) && (value < STORE_STATUS_ERROR_TRANSIENT_FAILURE)) || (value > STORE_STATUS_ERROR_END)) {
value=STORE_STATUS_ERROR_PERMANENT_FAILURE;
}
break;
case RESPONSE_STATUS:
if ((value > RESPONSE_STATUS_ERROR_TRANSIENT_PARTIAL_SUCCESS) && (value < RESPONSE_STATUS_ERROR_PERMANENT_FAILURE)) {
value=RESPONSE_STATUS_ERROR_TRANSIENT_FAILURE;
}
 else if (((value > RESPONSE_STATUS_ERROR_PERMANENT_LACK_OF_PREPAID) && (value <= RESPONSE_STATUS_ERROR_PERMANENT_END)) || (value < RESPONSE_STATUS_OK) || ((value > RESPONSE_STATUS_ERROR_UNSUPPORTED_MESSAGE) && (value < RESPONSE_STATUS_ERROR_TRANSIENT_FAILURE)) || (value > RESPONSE_STATUS_ERROR_PERMANENT_END)) {
value=RESPONSE_STATUS_ERROR_PERMANENT_FAILURE;
}
break;
case MMS_VERSION:
if ((value < MMS_VERSION_1_0) || (value > MMS_VERSION_1_3)) {
value=CURRENT_MMS_VERSION;
}
break;
case MESSAGE_TYPE:
if ((value < MESSAGE_TYPE_SEND_REQ) || (value > MESSAGE_TYPE_CANCEL_CONF)) {
throw new InvalidHeaderValueException("Invalid Octet value!");
}
break;
default :
throw new RuntimeException("Invalid header field!");
}
mHeaderMap.put(field,value);
}
