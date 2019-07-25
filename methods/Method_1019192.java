int build(){
  System.out.printf("|%n| Building all samples...%n|%n");
  run(".","java","--version");
  checkLicense("src/eclipse-public-license-2.0.java",".java");
  run("junit5-jupiter-starter-gradle","gradlew","clean","test");
  run("junit5-jupiter-starter-gradle-groovy","gradlew","clean","test");
  run("junit5-jupiter-starter-gradle-kotlin","gradlew","clean","test");
  run("junit5-jupiter-starter-maven","mvnw","clean","test");
  run("junit5-jupiter-starter-maven-kotlin","mvnw","clean","test");
  run("junit5-jupiter-starter-bazel","python","bazelisk.py","test","//...");
  run("junit5-jupiter-extensions","gradlew","clean","test");
  run("junit5-migration-gradle","gradlew","clean","test");
  run("junit5-migration-maven","mvnw","clean","test");
  run("junit5-multiple-engines","gradlew","clean","test");
  run("junit5-modular-world","jshell","build.jsh");
  System.out.printf("%n%n%n|%n| Done. Build exits with status = %d.%n|%n",status);
  return status;
}
