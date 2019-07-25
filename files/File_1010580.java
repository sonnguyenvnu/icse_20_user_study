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

/**
 * Identity of enumeration literal
 *
 * @author Radimir.Sorokin
 */
public class SEnumerationLiteralId {
  private final SDataTypeId myEnumId;
  private final long myLiteralId;

  public SEnumerationLiteralId(@NotNull SDataTypeId enumId, long literalId) {
    myEnumId = enumId;
    myLiteralId = literalId;
  }

  @NotNull
  public SDataTypeId getEnumerationId() {
    return myEnumId;
  }

  public long getIdValue () {
    return myLiteralId;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    SEnumerationLiteralId that = (SEnumerationLiteralId) o;

    if (myLiteralId != that.myLiteralId) return false;
    return myEnumId.equals(that.myEnumId);
  }

  @Override
  public int hashCode() {
    int result = myEnumId.hashCode();
    result = 31 * result + (int) (myLiteralId ^ (myLiteralId >>> 32));
    return result;
  }

  public String serialize() {
    return myEnumId.serialize() + "/" + myLiteralId;
  }

  public static SEnumerationLiteralId deserialize(String s) {
    int split = s.lastIndexOf('/');
    SDataTypeId enumId = SDataTypeId.deserialize(s.substring(0, split));
    long ref = Long.parseLong(s.substring(split + 1));
    return new SEnumerationLiteralId(enumId, ref);
  }

  @Override
  public String toString() {
    return serialize();
  }

}
