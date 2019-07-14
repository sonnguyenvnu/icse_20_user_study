private static String getCause(int statusCode){
  String cause;
switch (statusCode) {
case NOT_MODIFIED:
    cause="There was no new data to return.";
  break;
case BAD_REQUEST:
cause="The request was invalid. An accompanying error message will explain why. This is the status code will be returned during version 1.0 rate limiting(https://dev.twitter.com/pages/rate-limiting). In API v1.1, a request without authentication is considered invalid and you will get this response.";
break;
case UNAUTHORIZED:
cause="Authentication credentials (https://dev.twitter.com/pages/auth) were missing or incorrect. Ensure that you have set valid consumer key/secret, access token/secret, and the system clock is in sync.";
break;
case FORBIDDEN:
cause="The request is understood, but it has been refused. An accompanying error message will explain why. This code is used when requests are being denied due to update limits (https://support.twitter.com/articles/15364-about-twitter-limits-update-api-dm-and-following).";
break;
case NOT_FOUND:
cause="The URI requested is invalid or the resource requested, such as a user, does not exists. Also returned when the requested format is not supported by the requested method.";
break;
case NOT_ACCEPTABLE:
cause="Returned by the Search API when an invalid format is specified in the request.\n" + "Returned by the Streaming API when one or more of the parameters are not suitable for the resource. The track parameter, for example, would throw this error if:\n" + " The track keyword is too long or too short.\n" + " The bounding box specified is invalid.\n" + " No predicates defined for filtered resource, for example, neither track nor follow parameter defined.\n" + " Follow userid cannot be read.";
break;
case ENHANCE_YOUR_CLAIM:
cause="Returned by the Search and Trends API when you are being rate limited (https://dev.twitter.com/docs/rate-limiting).\n" + "Returned by the Streaming API:\n Too many login attempts in a short period of time.\n" + " Running too many copies of the same application authenticating with the same account name.";
break;
case UNPROCESSABLE_ENTITY:
cause="Returned when an image uploaded to POST account/update_profile_banner(https://dev.twitter.com/docs/api/1/post/account/update_profile_banner) is unable to be processed.";
break;
case TOO_MANY_REQUESTS:
cause="Returned in API v1.1 when a request cannot be served due to the application's rate limit having been exhausted for the resource. See Rate Limiting in API v1.1.(https://dev.twitter.com/docs/rate-limiting/1.1)";
break;
case INTERNAL_SERVER_ERROR:
cause="Something is broken. Please post to the group (https://dev.twitter.com/docs/support) so the Twitter team can investigate.";
break;
case BAD_GATEWAY:
cause="Twitter is down or being upgraded.";
break;
case SERVICE_UNAVAILABLE:
cause="The Twitter servers are up, but overloaded with requests. Try again later.";
break;
case GATEWAY_TIMEOUT:
cause="The Twitter servers are up, but the request couldn't be serviced due to some failure within our stack. Try again later.";
break;
default :
cause="";
}
return statusCode + ":" + cause;
}
