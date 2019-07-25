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
package jetbrains.mps.smodel.adapter.ids;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.mps.annotations.Immutable;

import java.util.Objects;

/**
 * Represents identity for language elements
 *
 * @author Radimir.Sorokin
 * @since 2018.3
 */
@Immutable
public class SElementId {

  @NotNull
  private final SLanguageId myLanguageId;

  private final long myIdValue;

  public SElementId(@NotNull SLanguageId languageId, long idValue) {
    myLanguageId = languageId;
    myIdValue = idValue;
  }

  @NotNull
  public SLanguageId getLanguageId() {
    return myLanguageId;
  }

  public long getIdValue() {
    return myIdValue;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || this.getClass() != o.getClass()) {
      return false;
    }
    SElementId that = (SElementId) o;
    return myIdValue == that.myIdValue &&
           myLanguageId.equals(that.myLanguageId);
  }

  @Override
  public int hashCode() {
    int result = myLanguageId.hashCode();
    result = 31 * result + (int) (myIdValue ^ (myIdValue >>> 32));
    return result;
  }

  public String serialize() {
    return myLanguageId.serialize() + "/" + myIdValue;
  }

  @Override
  public String toString() {
    return serialize();
  }

  protected static <T extends SElementId> T deserializeDefault(String s, Factory<T> factory) {
    int split = s.lastIndexOf('/');
    SLanguageId lang = SLanguageId.deserialize(s.substring(0, split));
    long idValue = Long.parseLong(s.substring(split + 1));
    return factory.create(lang, idValue);
  }

  protected interface Factory<T extends SElementId> {
    T create(SLanguageId languageId, long idValue);
  }
}
