/*
 * Copyright 2003-2014 JetBrains s.r.o.
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

public final class SReferenceLinkId  extends SConceptFeatureId{
  private final long myRefLinkId;

  public SReferenceLinkId(@NotNull SConceptId conceptId, long refLinkId) {
    super(conceptId);
    myRefLinkId = refLinkId;
  }

  public long getIdValue() {
    return myRefLinkId;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    SReferenceLinkId that = (SReferenceLinkId) o;

    if (myRefLinkId != that.myRefLinkId) return false;
    return getConceptId().equals(that.getConceptId());
  }

  @Override
  public int hashCode() {
    int result = getConceptId().hashCode();
    result = 31 * result + (int) (myRefLinkId ^ (myRefLinkId >>> 32));
    return result;
  }

  public String serialize() {
    return getConceptId().serialize() + "/" + myRefLinkId;
  }

  public static SReferenceLinkId deserialize(String s) {
    int split = s.lastIndexOf('/');
    SConceptId concept = SConceptId.deserialize(s.substring(0, split));
    long ref = Long.parseLong(s.substring(split + 1));
    return new SReferenceLinkId(concept, ref);
  }

  @Override
  public String toString() {
    return serialize();
  }
}
