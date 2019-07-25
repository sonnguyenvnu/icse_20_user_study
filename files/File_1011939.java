/*
 * Copyright 2003-2011 JetBrains s.r.o.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package jetbrains.mps.util;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * Utility to parse special idea.additional.classpath.txt file.
 * The paths in it points to the
 */
public final class ClassPathReader {
  private static final Logger LOG = LogManager.getLogger(ClassPathReader.class);

  private final File myAdditionalCPFile;
  private final List<String> myTypes;

  public ClassPathReader(@NotNull String homePath, @NotNull List<ClassType> types) {
    myAdditionalCPFile = calcFileLocation(homePath);
    myTypes = types.stream().map(ClassType::getTypeString).collect(Collectors.toList());
  }

  public ClassPathReader(@NotNull String homePath) {
    myAdditionalCPFile = calcFileLocation(homePath);
    myTypes = null;
  }

  private File calcFileLocation(@NotNull String homePath) {
    return new File(new File(homePath, "build"), "idea.additional.classpath.txt");
  }

  @NotNull
  public List<String> read() {
    List<String> result = new ArrayList<>();
    if (myAdditionalCPFile.exists()) {
      try (Scanner sc = new Scanner(myAdditionalCPFile, Charset.defaultCharset().name())) {
        boolean skipMode = false;
        while (sc.hasNextLine()) {
          String line = sc.nextLine().trim();
          if (line.startsWith(":")) {
            skipMode = myTypes != null && !myTypes.contains(line.substring(1));
            continue;
          }

          if (!skipMode) {
            result.add(line);
          }
        }
      } catch (FileNotFoundException ignored) {
        LOG.error("Problem while parsing class path", ignored);
      }
    } else {
      LOG.debug("The file with additional class path could not be found");
    }
    return result;
  }
}
