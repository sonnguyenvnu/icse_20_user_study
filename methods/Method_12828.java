/** 
 * Computes an "application hash", a reasonably unique identifier for an app. <p> This is currently used by Instant Run to prevent apps on a device from guessing the authentication token associated with an instant run developed app on the same device. <p> This method creates the "secret", the field in the AppInfo class which is used as a simple authentication token between the IDE and the app server. <p> This is not a cryptographically strong unique id; we could attempt to make a truly random number here, but we'd need to store that id somewhere such that subsequent builds will use the same secret, to avoid having the IDE and the app getting out of sync, and there isn't really a natural place for us to store the key to make it survive across a clean build. (One possibility is putting it into local.properties). <p> However, we have much simpler needs: we just need a key that can't be guessed from a hostile app on the developer's device, and it only has a few guesses (after providing the wrong secret to the server a few times, the server will shut down.) We can't rely on the package name along, since the port number is known, and the package name is discoverable by the hostile app (by querying the contents of /data/data/*). Therefore we also include facts that the hostile app can't know, such as as the path on the developer's machine to the app project and the name of the developer's machine, etc. The goal is for this secret to be reasonably stable (e.g. the same from build to build) yet not something an app could guess if it only has a couple of tries.
 */
public static long computeApplicationHash(@NonNull File projectDir){
  HashFunction hashFunction=Hashing.md5();
  Hasher hasher=hashFunction.newHasher();
  try {
    projectDir=projectDir.getCanonicalFile();
  }
 catch (  IOException ignore) {
  }
  String path=projectDir.getPath();
  hasher.putBytes(path.getBytes(Charsets.UTF_8));
  String user=System.getProperty("user.name");
  if (user != null) {
    hasher.putBytes(user.getBytes(Charsets.UTF_8));
  }
  return hasher.hash().asLong();
}
