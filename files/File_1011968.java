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
package jetbrains.mps.library;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Bean class for {@link jetbrains.mps.library.BaseLibraryManager.LibraryState}
 * Do not remove the default constructor and setters/getters
 */
public final class Library {
  private String myName;
  private String myPath;

  public Library() {
  }

  public Library(@NotNull String name) {
    myName = name;
  }

  public Library(@NotNull String name, @Nullable String path) {
    myName = name;
    myPath = path;
  }

  public String getName() {
    return myName;
  }

  @Nullable
  public String getPath() {
    return myPath;
  }

  public void setPath(@NotNull String path) {
    myPath = path;
  }

  public void setName(@NotNull String name) {
    myName = name;
  }

  public String toString() {
    return myName + " [" + myPath + "]";
  }

  @NotNull
  public Library copy() {
    return new Library(myName, myPath);
  }
}
