/*
 * Copyright 2003-2018 JetBrains s.r.o.
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
package jetbrains.mps.vfs;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.mps.annotations.Immutable;

/**
 * Represents a global file identifier (alike URL).
 * This is a correct way to store path to a file when the file under subject may be in more than one filesystem.
 * E.g. model root's paths might be in local FS or JRT FS, so correct way to store such a path in module file is QualifiedPath.
 * Having a QualifiedPath in hands, one can obtain a file using {@link VFSManager#getFile}
 * <p>
 * The {@link QualifiedPath} has two fields. FS identifies the filesystem to obtain path from, path is a
 */
@Immutable
public final class QualifiedPath {
  private static final String FS_DELIM = "://";

  private final String myFsId;
  private final String myPath;

  /**
   * @param fs   must consist of letters only
   * @param path could be any
   */
  public QualifiedPath(@NotNull String fs, @NotNull String path) {
    assert fs.chars().allMatch(Character::isLetter);
    myFsId = fs;
    myPath = path;
  }

  @NotNull
  public String getFsId() {
    return myFsId;
  }

  @NotNull
  public String getPath() {
    return myPath;
  }

  @Override
  public String toString() {
    //made not equal to serialize() not to provoke clients to use toString/deserialize pair
    return "QP{" +myFsId + '#' + myPath + '}';
  }

  @NotNull
  public String serialize(@Nullable MacroProcessor mp) {
    return myFsId + FS_DELIM + (mp == null ? myPath : mp.shrinkPath(myPath));
  }

  @NotNull
  public static QualifiedPath deserialize(@NotNull String s, @Nullable MacroProcessor mp) {
    int index = s.indexOf(FS_DELIM);
    if (index <= 0) {
      throw new IllegalStateException("Wrong format:" + s);
    }
    String path = s.substring(index + FS_DELIM.length());
    return new QualifiedPath(s.substring(0, index), mp == null ? path : mp.expandPath(path));
  }
}
