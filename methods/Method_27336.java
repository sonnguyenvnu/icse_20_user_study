private static boolean getInvitationIntent(@NonNull Uri uri){
  List<String> segments=uri.getPathSegments();
  return (segments != null && segments.size() == 3) && "invitations".equalsIgnoreCase(uri.getLastPathSegment());
}
