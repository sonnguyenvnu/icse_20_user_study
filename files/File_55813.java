/*
 * Copyright LWJGL. All rights reserved.
 * License terms: https://www.lwjgl.org/license
 * MACHINE GENERATED FILE, DO NOT EDIT
 */
package org.lwjgl.cuda;

import javax.annotation.*;

import java.nio.*;

import org.lwjgl.*;

import org.lwjgl.system.*;

import static org.lwjgl.system.APIUtil.*;
import static org.lwjgl.system.Checks.*;
import static org.lwjgl.system.JNI.*;
import static org.lwjgl.system.MemoryStack.*;
import static org.lwjgl.system.MemoryUtil.*;
import static org.lwjgl.system.Pointer.*;

import static org.lwjgl.cuda.CUDA.*;

/**
 * Contains bindings to the <a href="https://docs.nvidia.com/cuda/cuda-driver-api/index.html">CUDA Driver API</a>.
 * 
 * <p>This class includes functionality up to CUDA version 3.2, which is the minimum version compatible with the LWJGL bindings.</p>
 */
public class CU {

    /**
     * Flags for {@link #cuMemHostAlloc MemHostAlloc}.
     * 
     * <h5>Enum values:</h5>
     * 
     * <ul>
     * <li>{@link #CU_MEMHOSTALLOC_PORTABLE MEMHOSTALLOC_PORTABLE} - If set, host memory is portable between CUDA contexts.</li>
     * <li>{@link #CU_MEMHOSTALLOC_DEVICEMAP MEMHOSTALLOC_DEVICEMAP} - If set, host memory is mapped into CUDA address space and {@link #cuMemHostGetDevicePointer MemHostGetDevicePointer} may be called on the host pointer.</li>
     * <li>{@link #CU_MEMHOSTALLOC_WRITECOMBINED MEMHOSTALLOC_WRITECOMBINED} - 
     * If set, host memory is allocated as write-combined - fast to write, faster to DMA, slow to read except via SSE4 streaming load instruction
     * ({@code MOVNTDQA}).
     * </li>
     * </ul>
     */
    public static final int
        CU_MEMHOSTALLOC_PORTABLE      = 0x1,
        CU_MEMHOSTALLOC_DEVICEMAP     = 0x2,
        CU_MEMHOSTALLOC_WRITECOMBINED = 0x4;

    /**
     * Flags for {@link CU40#cuMemHostRegister MemHostRegister}.
     * 
     * <h5>Enum values:</h5>
     * 
     * <ul>
     * <li>{@link #CU_MEMHOSTREGISTER_PORTABLE MEMHOSTREGISTER_PORTABLE} - If set, host memory is portable between CUDA contexts.</li>
     * <li>{@link #CU_MEMHOSTREGISTER_DEVICEMAP MEMHOSTREGISTER_DEVICEMAP} - If set, host memory is mapped into CUDA address space and {@link #cuMemHostGetDevicePointer MemHostGetDevicePointer} may be called on the host pointer.</li>
     * <li>{@link #CU_MEMHOSTREGISTER_IOMEMORY MEMHOSTREGISTER_IOMEMORY} - 
     * If set, the passed memory pointer is treated as pointing to some memory-mapped I/O space, e.g. belonging to a third-party PCIe device.
     * 
     * <p>On Windows the flag is a no-op. On Linux that memory is marked as non cache-coherent for the GPU and is expected to be physically contiguous.
     * It may return {@link #CUDA_ERROR_NOT_PERMITTED} if run as an unprivileged user, {@link #CUDA_ERROR_NOT_SUPPORTED} on older Linux kernel versions. On all other
     * platforms, it is not supported and {@link #CUDA_ERROR_NOT_SUPPORTED} is returned.</p>
     * </li>
     * </ul>
     */
    public static final int
        CU_MEMHOSTREGISTER_PORTABLE  = 0x1,
        CU_MEMHOSTREGISTER_DEVICEMAP = 0x2,
        CU_MEMHOSTREGISTER_IOMEMORY  = 0x4;

    /**
     * <h5>Enum values:</h5>
     * 
     * <ul>
     * <li>{@link #CUDA_ARRAY3D_LAYERED CUDA_ARRAY3D_LAYERED} - 
     * If set, the CUDA array is a collection of layers, where each layer is either a 1D or a 2D array and the Depth member of {@link CUDA_ARRAY3D_DESCRIPTOR}
     * specifies the number of layers, not the depth of a 3D array.
     * </li>
     * <li>{@link #CUDA_ARRAY3D_2DARRAY CUDA_ARRAY3D_2DARRAY} - Deprecated, use {@link #CUDA_ARRAY3D_LAYERED}.</li>
     * <li>{@link #CUDA_ARRAY3D_SURFACE_LDST CUDA_ARRAY3D_SURFACE_LDST} - This flag must be set in order to bind a surface reference to the CUDA array.</li>
     * <li>{@link #CUDA_ARRAY3D_CUBEMAP CUDA_ARRAY3D_CUBEMAP} - 
     * If set, the CUDA array is a collection of six 2D arrays, representing faces of a cube. The width of such a CUDA array must be equal to its height,
     * and Depth must be six. If {@link #CUDA_ARRAY3D_LAYERED} flag is also set, then the CUDA array is a collection of cubemaps and Depth must be a multiple of
     * six.
     * </li>
     * <li>{@link #CUDA_ARRAY3D_TEXTURE_GATHER CUDA_ARRAY3D_TEXTURE_GATHER} - This flag must be set in order to perform texture gather operations on a CUDA array.</li>
     * <li>{@link #CUDA_ARRAY3D_DEPTH_TEXTURE CUDA_ARRAY3D_DEPTH_TEXTURE} - This flag if set indicates that the CUDA array is a DEPTH_TEXTURE.</li>
     * <li>{@link #CUDA_ARRAY3D_COLOR_ATTACHMENT CUDA_ARRAY3D_COLOR_ATTACHMENT} - This flag indicates that the CUDA array may be bound as a color target in an external graphics API.</li>
     * </ul>
     */
    public static final int
        CUDA_ARRAY3D_LAYERED          = 0x1,
        CUDA_ARRAY3D_2DARRAY          = 0x1,
        CUDA_ARRAY3D_SURFACE_LDST     = 0x2,
        CUDA_ARRAY3D_CUBEMAP          = 0x4,
        CUDA_ARRAY3D_TEXTURE_GATHER   = 0x8,
        CUDA_ARRAY3D_DEPTH_TEXTURE    = 0x10,
        CUDA_ARRAY3D_COLOR_ATTACHMENT = 0x20;

    /**
     * Flag for {@link #cuTexRefSetArray TexRefSetArray}.
     * 
     * <h5>Enum values:</h5>
     * 
     * <ul>
     * <li>{@link #CU_TRSA_OVERRIDE_FORMAT TRSA_OVERRIDE_FORMAT} - Override the {@code texref} format with a format inferred from the array.</li>
     * </ul>
     */
    public static final int CU_TRSA_OVERRIDE_FORMAT = 0x1;

    /**
     * Flag for {@link #cuTexRefSetFlags TexRefSetFlags}.
     * 
     * <h5>Enum values:</h5>
     * 
     * <ul>
     * <li>{@link #CU_TRSF_READ_AS_INTEGER TRSF_READ_AS_INTEGER} - Read the texture as integers rather than promoting the values to floats in the range {@code [0,1]}.</li>
     * <li>{@link #CU_TRSF_NORMALIZED_COORDINATES TRSF_NORMALIZED_COORDINATES} - Use normalized texture coordinates in the range {@code [0,1)} instead of {@code [0,dim)}.</li>
     * <li>{@link #CU_TRSF_SRGB TRSF_SRGB} - Perform {@code sRGB->linear} conversion during texture read.</li>
     * </ul>
     */
    public static final int
        CU_TRSF_READ_AS_INTEGER        = 0x1,
        CU_TRSF_NORMALIZED_COORDINATES = 0x2,
        CU_TRSF_SRGB                   = 0x10;

    /** For texture references loaded into the module, use default texunit from texture reference. */
    public static final int CU_PARAM_TR_DEFAULT = -1;

    /**
     * Context creation flags. ({@code CUctx_flags})
     * 
     * <h5>Enum values:</h5>
     * 
     * <ul>
     * <li>{@link #CU_CTX_SCHED_AUTO CTX_SCHED_AUTO} - Automatic scheduling</li>
     * <li>{@link #CU_CTX_SCHED_SPIN CTX_SCHED_SPIN} - Set spin as default scheduling</li>
     * <li>{@link #CU_CTX_SCHED_YIELD CTX_SCHED_YIELD} - Set yield as default scheduling</li>
     * <li>{@link #CU_CTX_SCHED_BLOCKING_SYNC CTX_SCHED_BLOCKING_SYNC} - Set blocking synchronization as default scheduling</li>
     * <li>{@link #CU_CTX_BLOCKING_SYNC CTX_BLOCKING_SYNC} - Set blocking synchronization as default scheduling. This flag was deprecated as of CUDA 4.0 and was replaced with {@link #CU_CTX_SCHED_BLOCKING_SYNC CTX_SCHED_BLOCKING_SYNC}.</li>
     * <li>{@link #CU_CTX_SCHED_MASK CTX_SCHED_MASK}</li>
     * <li>{@link #CU_CTX_MAP_HOST CTX_MAP_HOST} - Support mapped pinned allocations</li>
     * <li>{@link #CU_CTX_LMEM_RESIZE_TO_MAX CTX_LMEM_RESIZE_TO_MAX} - Keep local memory allocation after launch</li>
     * <li>{@link #CU_CTX_FLAGS_MASK CTX_FLAGS_MASK}</li>
     * </ul>
     */
    public static final int
        CU_CTX_SCHED_AUTO          = 0x0,
        CU_CTX_SCHED_SPIN          = 0x1,
        CU_CTX_SCHED_YIELD         = 0x2,
        CU_CTX_SCHED_BLOCKING_SYNC = 0x4,
        CU_CTX_BLOCKING_SYNC       = 0x4,
        CU_CTX_SCHED_MASK          = 0x7,
        CU_CTX_MAP_HOST            = 0x8,
        CU_CTX_LMEM_RESIZE_TO_MAX  = 0x10,
        CU_CTX_FLAGS_MASK          = 0x1F;

    /**
     * Stream creation flags. ({@code CUstream_flags})
     * 
     * <h5>Enum values:</h5>
     * 
     * <ul>
     * <li>{@link #CU_STREAM_DEFAULT STREAM_DEFAULT} - Default stream flag</li>
     * <li>{@link #CU_STREAM_NON_BLOCKING STREAM_NON_BLOCKING} - Stream does not synchronize with stream 0 (the {@code NULL} stream)</li>
     * </ul>
     */
    public static final int
        CU_STREAM_DEF