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
package jetbrains.mps.smodel.persistence.def.v9;

import jetbrains.mps.persistence.registry.IdInfoRegistry;
import jetbrains.mps.smodel.JavaFriendlyBase64;
import jetbrains.mps.smodel.StringBasedIdForJavaStubMethods;
import jetbrains.mps.smodel.SNodeId.Foreign;
import jetbrains.mps.smodel.SNodeId.Regular;
import jetbrains.mps.smodel.StaticReference;
import jetbrains.mps.smodel.adapter.ids.SConceptId;
import jetbrains.mps.smodel.adapter.ids.SContainmentLinkId;
import jetbrains.mps.smodel.adapter.ids.SLanguageId;
import jetbrains.mps.smodel.adapter.ids.SPropertyId;
import jetbrains.mps.smodel.adapter.ids.SReferenceLinkId;
import jetbrains.mps.util.Pair;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.mps.openapi.model.SModelReference;
import org.jetbrains.mps.openapi.model.SNodeId;
import org.jetbrains.mps.openapi.model.SReference;
import org.jetbrains.mps.openapi.module.SModuleReference;
import org.jetbrains.mps.openapi.persistence.PersistenceFacade;

/**
 * Intention is to keep all serialize/de-serialize code in a single place.
 * <p/>
 * FIXME this class is public merely for the sake of GoToNodeById action. Once this encoder is part of persistence API, action shall use API, not this class
 * <p/>
 * This class is not thread-safe, uses internal buffers to save memory on (de-)serialize, do not share it between thread (although unlikely to happen as
 * persistence demands single thread access).
 */
public final class IdEncoder implements IdInfoRegistry.IndexEncoder {
  // separator for import in serialized reference target
  private static final char REF_TARGET_IMPORT_SEPARATOR = ':';
  private static final String DYNAMIC_REFERENCE_ID = "^";
  private final JavaFriendlyBase64 myBase64 = new JavaFriendlyBase64();

  public IdEncoder() {
  }

  public String toText(SLanguageId langId) {
    return langId.serialize();
  }

  public SLanguageId parseLanguageId(String text) {
    return SLanguageId.deserialize(text);
  }

  public String toText(SConceptId conceptId) {
    return Long.toString(conceptId.getIdValue());
  }

  public SConceptId parseConceptId(SLanguageId lang, String text) {
    return new SConceptId(lang, Long.parseLong(text));
  }

  public String toText(SPropertyId propertyId) {
    return Long.toString(propertyId.getIdValue());
  }

  public SPropertyId parsePropertyId(SConceptId concept, String text) {
    return new SPropertyId(concept, Long.parseLong(text));
  }

  public String toText(SReferenceLinkId linkId) {
    return Long.toString(linkId.getIdValue());
  }

  public SReferenceLinkId parseAssociation(SConceptId concept, String text) {
    return new SReferenceLinkId(concept, Long.parseLong(text));
  }

  public String toText(SContainmentLinkId linkId) {
    return Long.toString(linkId.getIdValue());
  }

  public SContainmentLinkId parseAggregation(SConceptId concept, String text) {
    return new SContainmentLinkId(concept, Long.parseLong(text));
  }

  public String toText(SNodeId nodeId) {
    if (nodeId instanceof Regular) {
      final long v = ((Regular) nodeId).getId();
      return myBase64.toString(v);
    }
    // fall-through
    return nodeId.toString();
  }

  public SNodeId parseNodeId(String text) throws EncodingException {
    try {
      if (!text.startsWith(Foreign.ID_PREFIX) && !text.startsWith(StringBasedIdForJavaStubMethods.ID_PREFIX)) {
        long v = myBase64.parseLong(text);
        return new Regular(v);
      }
      // fall-through
      return jetbrains.mps.smodel.SNodeId.fromString(text);
    } catch (IllegalArgumentException ex) {
      throw new EncodingException(ex.getMessage());
    }
  }

  public String toText(SModelReference mr) {
    return PersistenceFacade.getInstance().asString(mr);
  }

  public SModelReference parseModelReference(String text) {
    return PersistenceFacade.getInstance().createModelReference(text);
  }

  public String toText(SModuleReference ref) {
//    return PersistenceFacade.getInstance().asString(ref); FIXME add counterpart for createModuleReference
    return ref.toString();
  }

  public SModuleReference parseModuleReference(String text) {
    return PersistenceFacade.getInstance().createModuleReference(text);
  }

  public String toTextLocal(SReference ref) {
    String target;
    if (ref instanceof StaticReference) {
      final SNodeId targetNodeId = ref.getTargetNodeId();
      if (targetNodeId == null) {
        target = DYNAMIC_REFERENCE_ID;
      } else {
        target = toText(targetNodeId);
      }
    } else {
      target = DYNAMIC_REFERENCE_ID;
    }
    return target;
  }

  /**
   * Local references are saved in a form of serialized node id, or '^' for dynamic references.
   * External references are prefixed with import index and ':'.
   * <p/>
   * NOTE, the way import index and nodeId value are serialized is expected to never include ':' separator char
   */
  public String toTextExternal(@NotNull ImportsHelper imports, @NotNull SReference ref) {
    String target = toTextLocal(ref);
    SModelReference targetModel = ref.getTargetSModelReference();

    if (targetModel == null) {
      return REF_TARGET_IMPORT_SEPARATOR + target;
    }

    String index = imports.getIndex(targetModel);
    assert index != null : "model " + targetModel + " not found in index";
    return index + REF_TARGET_IMPORT_SEPARATOR + target;
  }

  @Nullable
  public SNodeId parseLocalNodeReference(String text) {
    if (DYNAMIC_REFERENCE_ID.equals(text)) {
      return null;
    }
    try {
      return parseNodeId(text);
    } catch (EncodingException e) {
      throw new IllegalArgumentException(e);
    }
  }

  public Pair<SModelReference, SNodeId> parseExternalNodeReference(ImportsHelper imports, String referenceTarget) {
    int separatorIndex = referenceTarget.indexOf(REF_TARGET_IMPORT_SEPARATOR);
    assert separatorIndex >= 0;
    final SModelReference modelRef = separatorIndex == 0 ? null : imports.getModelReference(referenceTarget.substring(0, separatorIndex));
    SNodeId nodeId = parseLocalNodeReference(referenceTarget.substring(separatorIndex + 1));
    return new Pair<>(modelRef, nodeId);
  }

  /**
   * Dedicated alternative of the {@link #parseExternalNodeReference(ImportsHelper, String)} that cares about target node id only, for indexing purposes,
   * see {@link jetbrains.mps.smodel.persistence.def.v9.Indexer9}
   */
  @Nullable
  SNodeId parseExternalNodeReference(String referenceTarget) {
    int separatorIndex = referenceTarget.indexOf(REF_TARGET_IMPORT_SEPARATOR);
    assert separatorIndex >= 0;
    return parseLocalNodeReference(referenceTarget.substring(separatorIndex + 1));
  }


  @Override
  public String index(int key) {
    return myBase64.indexValue(key);
  }

  public static class EncodingException extends Exception {
    public EncodingException(String message) {
      super(message);
    }
  }
}
