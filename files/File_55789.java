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
        CU_STREAM_DEFAULT      = 0x0,
        CU_STREAM_NON_BLOCKING = 0x1;

    /**
     * Event creation flags. ({@code CUevent_flags})
     * 
     * <h5>Enum values:</h5>
     * 
     * <ul>
     * <li>{@link #CU_EVENT_DEFAULT EVENT_DEFAULT} - Default event flag</li>
     * <li>{@link #CU_EVENT_BLOCKING_SYNC EVENT_BLOCKING_SYNC} - Event uses blocking synchronization</li>
     * <li>{@link #CU_EVENT_DISABLE_TIMING EVENT_DISABLE_TIMING} - Event will not record timing data</li>
     * <li>{@link #CU_EVENT_INTERPROCESS EVENT_INTERPROCESS} - Event is suitable for interprocess use. {@link #CU_EVENT_DISABLE_TIMING EVENT_DISABLE_TIMING} must be set</li>
     * </ul>
     */
    public static final int
        CU_EVENT_DEFAULT        = 0x0,
        CU_EVENT_BLOCKING_SYNC  = 0x1,
        CU_EVENT_DISABLE_TIMING = 0x2,
        CU_EVENT_INTERPROCESS   = 0x4;

    /**
     * Array formats. ({@code CUarray_format})
     * 
     * <h5>Enum values:</h5>
     * 
     * <ul>
     * <li>{@link #CU_AD_FORMAT_UNSIGNED_INT8 AD_FORMAT_UNSIGNED_INT8} - Unsigned 8-bit integers</li>
     * <li>{@link #CU_AD_FORMAT_UNSIGNED_INT16 AD_FORMAT_UNSIGNED_INT16} - Unsigned 16-bit integers</li>
     * <li>{@link #CU_AD_FORMAT_UNSIGNED_INT32 AD_FORMAT_UNSIGNED_INT32} - Unsigned 32-bit integers</li>
     * <li>{@link #CU_AD_FORMAT_SIGNED_INT8 AD_FORMAT_SIGNED_INT8} - Signed 8-bit integers</li>
     * <li>{@link #CU_AD_FORMAT_SIGNED_INT16 AD_FORMAT_SIGNED_INT16} - Signed 16-bit integers</li>
     * <li>{@link #CU_AD_FORMAT_SIGNED_INT32 AD_FORMAT_SIGNED_INT32} - Signed 32-bit integers</li>
     * <li>{@link #CU_AD_FORMAT_HALF AD_FORMAT_HALF} - 16-bit floating point</li>
     * <li>{@link #CU_AD_FORMAT_FLOAT AD_FORMAT_FLOAT} - 32-bit floating point</li>
     * </ul>
     */
    public static final int
        CU_AD_FORMAT_UNSIGNED_INT8  = 0x1,
        CU_AD_FORMAT_UNSIGNED_INT16 = 0x2,
        CU_AD_FORMAT_UNSIGNED_INT32 = 0x3,
        CU_AD_FORMAT_SIGNED_INT8    = 0x8,
        CU_AD_FORMAT_SIGNED_INT16   = 0x9,
        CU_AD_FORMAT_SIGNED_INT32   = 0xA,
        CU_AD_FORMAT_HALF           = 0x10,
        CU_AD_FORMAT_FLOAT          = 0x20;

    /**
     * Texture reference addressing modes. ({@code CUaddress_mode})
     * 
     * <h5>Enum values:</h5>
     * 
     * <ul>
     * <li>{@link #CU_TR_ADDRESS_MODE_WRAP TR_ADDRESS_MODE_WRAP} - Wrapping address mode</li>
     * <li>{@link #CU_TR_ADDRESS_MODE_CLAMP TR_ADDRESS_MODE_CLAMP} - Clamp to edge address mode</li>
     * <li>{@link #CU_TR_ADDRESS_MODE_MIRROR TR_ADDRESS_MODE_MIRROR} - Mirror address mode</li>
     * <li>{@link #CU_TR_ADDRESS_MODE_BORDER TR_ADDRESS_MODE_BORDER} - Border address mode</li>
     * </ul>
     */
    public static final int
        CU_TR_ADDRESS_MODE_WRAP   = 0x0,
        CU_TR_ADDRESS_MODE_CLAMP  = 0x1,
        CU_TR_ADDRESS_MODE_MIRROR = 0x2,
        CU_TR_ADDRESS_MODE_BORDER = 0x3;

    /**
     * Texture reference filtering modes. ({@code CUfilter_mode})
     * 
     * <h5>Enum values:</h5>
     * 
     * <ul>
     * <li>{@link #CU_TR_FILTER_MODE_POINT TR_FILTER_MODE_POINT} - Point filter mode</li>
     * <li>{@link #CU_TR_FILTER_MODE_LINEAR TR_FILTER_MODE_LINEAR} - Linear filter mode</li>
     * </ul>
     */
    public static final int
        CU_TR_FILTER_MODE_POINT  = 0x0,
        CU_TR_FILTER_MODE_LINEAR = 0x1;

    /**
     * Device properties. ({@code CUdevice_attribute})
     * 
     * <h5>Enum values:</h5>
     * 
     * <ul>
     * <li>{@link #CU_DEVICE_ATTRIBUTE_MAX_THREADS_PER_BLOCK DEVICE_ATTRIBUTE_MAX_THREADS_PER_BLOCK} - Maximum number of threads per block</li>
     * <li>{@link #CU_DEVICE_ATTRIBUTE_MAX_BLOCK_DIM_X DEVICE_ATTRIBUTE_MAX_BLOCK_DIM_X} - Maximum block dimension X</li>
     * <li>{@link #CU_DEVICE_ATTRIBUTE_MAX_BLOCK_DIM_Y DEVICE_ATTRIBUTE_MAX_BLOCK_DIM_Y} - Maximum block dimension Y</li>
     * <li>{@link #CU_DEVICE_ATTRIBUTE_MAX_BLOCK_DIM_Z DEVICE_ATTRIBUTE_MAX_BLOCK_DIM_Z} - Maximum block dimension Z</li>
     * <li>{@link #CU_DEVICE_ATTRIBUTE_MAX_GRID_DIM_X DEVICE_ATTRIBUTE_MAX_GRID_DIM_X} - Maximum grid dimension X</li>
     * <li>{@link #CU_DEVICE_ATTRIBUTE_MAX_GRID_DIM_Y DEVICE_ATTRIBUTE_MAX_GRID_DIM_Y} - Maximum grid dimension Y</li>
     * <li>{@link #CU_DEVICE_ATTRIBUTE_MAX_GRID_DIM_Z DEVICE_ATTRIBUTE_MAX_GRID_DIM_Z} - Maximum grid dimension Z</li>
     * <li>{@link #CU_DEVICE_ATTRIBUTE_MAX_SHARED_MEMORY_PER_BLOCK DEVICE_ATTRIBUTE_MAX_SHARED_MEMORY_PER_BLOCK} - Maximum shared memory available per block in bytes</li>
     * <li>{@link #CU_DEVICE_ATTRIBUTE_SHARED_MEMORY_PER_BLOCK DEVICE_ATTRIBUTE_SHARED_MEMORY_PER_BLOCK} - Deprecated, use {@link #CU_DEVICE_ATTRIBUTE_MAX_SHARED_MEMORY_PER_BLOCK DEVICE_ATTRIBUTE_MAX_SHARED_MEMORY_PER_BLOCK}</li>
     * <li>{@link #CU_DEVICE_ATTRIBUTE_TOTAL_CONSTANT_MEMORY DEVICE_ATTRIBUTE_TOTAL_CONSTANT_MEMORY} - Memory available on device for {@code __constant__} variables in a CUDA C kernel in bytes</li>
     * <li>{@link #CU_DEVICE_ATTRIBUTE_WARP_SIZE DEVICE_ATTRIBUTE_WARP_SIZE} - Warp size in threads</li>
     * <li>{@link #CU_DEVICE_ATTRIBUTE_MAX_PITCH DEVICE_ATTRIBUTE_MAX_PITCH} - Maximum pitch in bytes allowed by memory copies</li>
     * <li>{@link #CU_DEVICE_ATTRIBUTE_MAX_REGISTERS_PER_BLOCK DEVICE_ATTRIBUTE_MAX_REGISTERS_PER_BLOCK} - Maximum number of 32-bit registers available per block</li>
     * <li>{@link #CU_DEVICE_ATTRIBUTE_REGISTERS_PER_BLOCK DEVICE_ATTRIBUTE_REGISTERS_PER_BLOCK} - Deprecated, use {@link #CU_DEVICE_ATTRIBUTE_MAX_REGISTERS_PER_BLOCK DEVICE_ATTRIBUTE_MAX_REGISTERS_PER_BLOCK}</li>
     * <li>{@link #CU_DEVICE_ATTRIBUTE_CLOCK_RATE DEVICE_ATTRIBUTE_CLOCK_RATE} - Typical clock frequency in kilohertz</li>
     * <li>{@link #CU_DEVICE_ATTRIBUTE_TEXTURE_ALIGNMENT DEVICE_ATTRIBUTE_TEXTURE_ALIGNMENT} - Alignment requirement for textures</li>
     * <li>{@link #CU_DEVICE_ATTRIBUTE_GPU_OVERLAP DEVICE_ATTRIBUTE_GPU_OVERLAP} - Device can possibly copy memory and execute a kernel concurrently. Deprecated. Use instead {@link #CU_DEVICE_ATTRIBUTE_ASYNC_ENGINE_COUNT DEVICE_ATTRIBUTE_ASYNC_ENGINE_COUNT}.</li>
     * <li>{@link #CU_DEVICE_ATTRIBUTE_MULTIPROCESSOR_COUNT DEVICE_ATTRIBUTE_MULTIPROCESSOR_COUNT} - Number of multiprocessors on device</li>
     * <li>{@link #CU_DEVICE_ATTRIBUTE_KERNEL_EXEC_TIMEOUT DEVICE_ATTRIBUTE_KERNEL_EXEC_TIMEOUT} - Specifies whether there is a run time limit on kernels</li>
     * <li>{@link #CU_DEVICE_ATTRIBUTE_INTEGRATED DEVICE_ATTRIBUTE_INTEGRATED} - Device is integrated with host memory</li>
     * <li>{@link #CU_DEVICE_ATTRIBUTE_CAN_MAP_HOST_MEMORY DEVICE_ATTRIBUTE_CAN_MAP_HOST_MEMORY} - Device can map host memory into CUDA address space</li>
     * <li>{@link #CU_DEVICE_ATTRIBUTE_COMPUTE_MODE DEVICE_ATTRIBUTE_COMPUTE_MODE} - Compute mode (See {@code CUcomputemode} for details)</li>
     * <li>{@link #CU_DEVICE_ATTRIBUTE_MAXIMUM_TEXTURE1D_WIDTH DEVICE_ATTRIBUTE_MAXIMUM_TEXTURE1D_WIDTH} - Maximum 1D texture width</li>
     * <li>{@link #CU_DEVICE_ATTRIBUTE_MAXIMUM_TEXTURE2D_WIDTH DEVICE_ATTRIBUTE_MAXIMUM_TEXTURE2D_WIDTH} - Maximum 2D texture width</li>
     * <li>{@link #CU_DEVICE_ATTRIBUTE_MAXIMUM_TEXTURE2D_HEIGHT DEVICE_ATTRIBUTE_MAXIMUM_TEXTURE2D_HEIGHT} - Maximum 2D texture height</li>
     * <li>{@link #CU_DEVICE_ATTRIBUTE_MAXIMUM_TEXTURE3D_WIDTH DEVICE_ATTRIBUTE_MAXIMUM_TEXTURE3D_WIDTH} - Maximum 3D texture width</li>
     * <li>{@link #CU_DEVICE_ATTRIBUTE_MAXIMUM_TEXTURE3D_HEIGHT DEVICE_ATTRIBUTE_MAXIMUM_TEXTURE3D_HEIGHT} - Maximum 3D texture height</li>
     * <li>{@link #CU_DEVICE_ATTRIBUTE_MAXIMUM_TEXTURE3D_DEPTH DEVICE_ATTRIBUTE_MAXIMUM_TEXTURE3D_DEPTH} - Maximum 3D texture depth</li>
     * <li>{@link #CU_DEVICE_ATTRIBUTE_MAXIMUM_TEXTURE2D_LAYERED_WIDTH DEVICE_ATTRIBUTE_MAXIMUM_TEXTURE2D_LAYERED_WIDTH} - Maximum 2D layered texture width</li>
     * <li>{@link #CU_DEVICE_ATTRIBUTE_MAXIMUM_TEXTURE2D_LAYERED_HEIGHT DEVICE_ATTRIBUTE_MAXIMUM_TEXTURE2D_LAYERED_HEIGHT} - Maximum 2D layered texture height</li>
     * <li>{@link #CU_DEVICE_ATTRIBUTE_MAXIMUM_TEXTURE2D_LAYERED_LAYERS DEVICE_ATTRIBUTE_MAXIMUM_TEXTURE2D_LAYERED_LAYERS} - Maximum layers in a 2D layered texture</li>
     * <li>{@link #CU_DEVICE_ATTRIBUTE_MAXIMUM_TEXTURE2D_ARRAY_WIDTH DEVICE_ATTRIBUTE_MAXIMUM_TEXTURE2D_ARRAY_WIDTH} - Deprecated, use {@link #CU_DEVICE_ATTRIBUTE_MAXIMUM_TEXTURE2D_LAYERED_WIDTH DEVICE_ATTRIBUTE_MAXIMUM_TEXTURE2D_LAYERED_WIDTH}</li>
     * <li>{@link #CU_DEVICE_ATTRIBUTE_MAXIMUM_TEXTURE2D_ARRAY_HEIGHT DEVICE_ATTRIBUTE_MAXIMUM_TEXTURE2D_ARRAY_HEIGHT} - Deprecated, use {@link #CU_DEVICE_ATTRIBUTE_MAXIMUM_TEXTURE2D_LAYERED_HEIGHT DEVICE_ATTRIBUTE_MAXIMUM_TEXTURE2D_LAYERED_HEIGHT}</li>
     * <li>{@link #CU_DEVICE_ATTRIBUTE_MAXIMUM_TEXTURE2D_ARRAY_NUMSLICES DEVICE_ATTRIBUTE_MAXIMUM_TEXTURE2D_ARRAY_NUMSLICES} - Deprecated, use {@link #CU_DEVICE_ATTRIBUTE_MAXIMUM_TEXTURE2D_LAYERED_LAYERS DEVICE_ATTRIBUTE_MAXIMUM_TEXTURE2D_LAYERED_LAYERS}</li>
     * <li>{@link #CU_DEVICE_ATTRIBUTE_SURFACE_ALIGNMENT DEVICE_ATTRIBUTE_SURFACE_ALIGNMENT} - Alignment requirement for surfaces</li>
     * <li>{@link #CU_DEVICE_ATTRIBUTE_CONCURRENT_KERNELS DEVICE_ATTRIBUTE_CONCURRENT_KERNELS} - Device can possibly execute multiple kernels concurrently</li>
     * <li>{@link #CU_DEVICE_ATTRIBUTE_ECC_ENABLED DEVICE_ATTRIBUTE_ECC_ENABLED} - Device has ECC support enabled</li>
     * <li>{@link #CU_DEVICE_ATTRIBUTE_PCI_BUS_ID DEVICE_ATTRIBUTE_PCI_BUS_ID} - PCI bus ID of the device</li>
     * <li>{@link #CU_DEVICE_ATTRIBUTE_PCI_DEVICE_ID DEVICE_ATTRIBUTE_PCI_DEVICE_ID} - PCI device ID of the device</li>
     * <li>{@link #CU_DEVICE_ATTRIBUTE_TCC_DRIVER DEVICE_ATTRIBUTE_TCC_DRIVER} - Device is using TCC driver model</li>
     * <li>{@link #CU_DEVICE_ATTRIBUTE_MEMORY_CLOCK_RATE DEVICE_ATTRIBUTE_MEMORY_CLOCK_RATE} - Peak memory clock frequency in kilohertz</li>
     * <li>{@link #CU_DEVICE_ATTRIBUTE_GLOBAL_MEMORY_BUS_WIDTH DEVICE_ATTRIBUTE_GLOBAL_MEMORY_BUS_WIDTH} - Global memory bus width in bits</li>
     * <li>{@link #CU_DEVICE_ATTRIBUTE_L2_CACHE_SIZE DEVICE_ATTRIBUTE_L2_CACHE_SIZE} - Size of L2 cache in bytes</li>
     * <li>{@link #CU_DEVICE_ATTRIBUTE_MAX_THREADS_PER_MULTIPROCESSOR DEVICE_ATTRIBUTE_MAX_THREADS_PER_MULTIPROCESSOR} - Maximum resident threads per multiprocessor</li>
     * <li>{@link #CU_DEVICE_ATTRIBUTE_ASYNC_ENGINE_COUNT DEVICE_ATTRIBUTE_ASYNC_ENGINE_COUNT} - Number of asynchronous engines</li>
     * <li>{@link #CU_DEVICE_ATTRIBUTE_UNIFIED_ADDRESSING DEVICE_ATTRIBUTE_UNIFIED_ADDRESSING} - Device shares a unified address space with the host</li>
     * <li>{@link #CU_DEVICE_ATTRIBUTE_MAXIMUM_TEXTURE1D_LAYERED_WIDTH DEVICE_ATTRIBUTE_MAXIMUM_TEXTURE1D_LAYERED_WIDTH} - Maximum 1D layered texture width</li>
     * <li>{@link #CU_DEVICE_ATTRIBUTE_MAXIMUM_TEXTURE1D_LAYERED_LAYERS DEVICE_ATTRIBUTE_MAXIMUM_TEXTURE1D_LAYERED_LAYERS} - Maximum layers in a 1D layered texture</li>
     * <li>{@link #CU_DEVICE_ATTRIBUTE_CAN_TEX2D_GATHER DEVICE_ATTRIBUTE_CAN_TEX2D_GATHER} - Deprecated, do not use.</li>
     * <li>{@link #CU_DEVICE_ATTRIBUTE_MAXIMUM_TEXTURE2D_GATHER_WIDTH DEVICE_ATTRIBUTE_MAXIMUM_TEXTURE2D_GATHER_WIDTH} - Maximum 2D texture width if {@link #CUDA_ARRAY3D_TEXTURE_GATHER} is set</li>
     * <li>{@link #CU_DEVICE_ATTRIBUTE_MAXIMUM_TEXTURE2D_GATHER_HEIGHT DEVICE_ATTRIBUTE_MAXIMUM_TEXTURE2D_GATHER_HEIGHT} - Maximum 2D texture height if {@link #CUDA_ARRAY3D_TEXTURE_GATHER} is set</li>
     * <li>{@link #CU_DEVICE_ATTRIBUTE_MAXIMUM_TEXTURE3D_WIDTH_ALTERNATE DEVICE_ATTRIBUTE_MAXIMUM_TEXTURE3D_WIDTH_ALTERNATE} - Alternate maximum 3D texture width</li>
     * <li>{@link #CU_DEVICE_ATTRIBUTE_MAXIMUM_TEXTURE3D_HEIGHT_ALTERNATE DEVICE_ATTRIBUTE_MAXIMUM_TEXTURE3D_HEIGHT_ALTERNATE} - Alternate maximum 3D texture height</li>
     * <li>{@link #CU_DEVICE_ATTRIBUTE_MAXIMUM_TEXTURE3D_DEPTH_ALTERNATE DEVICE_ATTRIBUTE_MAXIMUM_TEXTURE3D_DEPTH_ALTERNATE} - Alternate maximum 3D texture depth</li>
     * <li>{@link #CU_DEVICE_ATTRIBUTE_PCI_DOMAIN_ID DEVICE_ATTRIBUTE_PCI_DOMAIN_ID} - PCI domain ID of the device</li>
     * <li>{@link #CU_DEVICE_ATTRIBUTE_TEXTURE_PITCH_ALIGNMENT DEVICE_ATTRIBUTE_TEXTURE_PITCH_ALIGNMENT} - Pitch alignment requirement for textures</li>
     * <li>{@link #CU_DEVICE_ATTRIBUTE_MAXIMUM_TEXTURECUBEMAP_WIDTH DEVICE_ATTRIBUTE_MAXIMUM_TEXTURECUBEMAP_WIDTH} - Maximum cubemap texture width/height</li>
     * <li>{@link #CU_DEVICE_ATTRIBUTE_MAXIMUM_TEXTURECUBEMAP_LAYERED_WIDTH DEVICE_ATTRIBUTE_MAXIMUM_TEXTURECUBEMAP_LAYERED_WIDTH} - Maximum cubemap layered texture width/height</li>
     * <li>{@link #CU_DEVICE_ATTRIBUTE_MAXIMUM_TEXTURECUBEMAP_LAYERED_LAYERS DEVICE_ATTRIBUTE_MAXIMUM_TEXTURECUBEMAP_LAYERED_LAYERS} - Maximum layers in a cubemap layered texture</li>
     * <li>{@link #CU_DEVICE_ATTRIBUTE_MAXIMUM_SURFACE1D_WIDTH DEVICE_ATTRIBUTE_MAXIMUM_SURFACE1D_WIDTH} - Maximum 1D surface width</li>
     * <li>{@link #CU_DEVICE_ATTRIBUTE_MAXIMUM_SURFACE2D_WIDTH DEVICE_ATTRIBUTE_MAXIMUM_SURFACE2D_WIDTH} - Maximum 2D surface width</li>
     * <li>{@link #CU_DEVICE_ATTRIBUTE_MAXIMUM_SURFACE2D_HEIGHT DEVICE_ATTRIBUTE_MAXIMUM_SURFACE2D_HEIGHT} - Maximum 2D surface height</li>
     * <li>{@link #CU_DEVICE_ATTRIBUTE_MAXIMUM_SURFACE3D_WIDTH DEVICE_ATTRIBUTE_MAXIMUM_SURFACE3D_WIDTH} - Maximum 3D surface width</li>
     * <li>{@link #CU_DEVICE_ATTRIBUTE_MAXIMUM_SURFACE3D_HEIGHT DEVICE_ATTRIBUTE_MAXIMUM_SURFACE3D_HEIGHT} - Maximum 3D surface height</li>
     * <li>{@link #CU_DEVICE_ATTRIBUTE_MAXIMUM_SURFACE3D_DEPTH DEVICE_ATTRIBUTE_MAXIMUM_SURFACE3D_DEPTH} - Maximum 3D surface depth</li>
     * <li>{@link #CU_DEVICE_ATTRIBUTE_MAXIMUM_SURFACE1D_LAYERED_WIDTH DEVICE_ATTRIBUTE_MAXIMUM_SURFACE1D_LAYERED_WIDTH} - Maximum 1D layered surface width</li>
     * <li>{@link #CU_DEVICE_ATTRIBUTE_MAXIMUM_SURFACE1D_LAYERED_LAYERS DEVICE_ATTRIBUTE_MAXIMUM_SURFACE1D_LAYERED_LAYERS} - Maximum layers in a 1D layered surface</li>
     * <li>{@link #CU_DEVICE_ATTRIBUTE_MAXIMUM_SURFACE2D_LAYERED_WIDTH DEVICE_ATTRIBUTE_MAXIMUM_SURFACE2D_LAYERED_WIDTH} - Maximum 2D layered surface width</li>
     * <li>{@link #CU_DEVICE_ATTRIBUTE_MAXIMUM_SURFACE2D_LAYERED_HEIGHT DEVICE_ATTRIBUTE_MAXIMUM_SURFACE2D_LAYERED_HEIGHT} - Maximum 2D layered surface height</li>
     * <li>{@link #CU_DEVICE_ATTRIBUTE_MAXIMUM_SURFACE2D_LAYERED_LAYERS DEVICE_ATTRIBUTE_MAXIMUM_SURFACE2D_LAYERED_LAYERS} - Maximum layers in a 2D layered surface</li>
     * <li>{@link #CU_DEVICE_ATTRIBUTE_MAXIMUM_SURFACECUBEMAP_WIDTH DEVICE_ATTRIBUTE_MAXIMUM_SURFACECUBEMAP_WIDTH} - Maximum cubemap surface width</li>
     * <li>{@link #CU_DEVICE_ATTRIBUTE_MAXIMUM_SURFACECUBEMAP_LAYERED_WIDTH DEVICE_ATTRIBUTE_MAXIMUM_SURFACECUBEMAP_LAYERED_WIDTH} - Maximum cubemap layered surface width</li>
     * <li>{@link #CU_DEVICE_ATTRIBUTE_MAXIMUM_SURFACECUBEMAP_LAYERED_LAYERS DEVICE_ATTRIBUTE_MAXIMUM_SURFACECUBEMAP_LAYERED_LAYERS} - Maximum layers in a cubemap layered surface</li>
     * <li>{@link #CU_DEVICE_ATTRIBUTE_MAXIMUM_TEXTURE1D_LINEAR_WIDTH DEVICE_ATTRIBUTE_MAXIMUM_TEXTURE1D_LINEAR_WIDTH} - Maximum 1D linear texture width</li>
     * <li>{@link #CU_DEVICE_ATTRIBUTE_MAXIMUM_TEXTURE2D_LINEAR_WIDTH DEVICE_ATTRIBUTE_MAXIMUM_TEXTURE2D_LINEAR_WIDTH} - Maximum 2D linear texture width</li>
     * <li>{@link #CU_DEVICE_ATTRIBUTE_MAXIMUM_TEXTURE2D_LINEAR_HEIGHT DEVICE_ATTRIBUTE_MAXIMUM_TEXTURE2D_LINEAR_HEIGHT} - Maximum 2D linear texture height</li>
     * <li>{@link #CU_DEVICE_ATTRIBUTE_MAXIMUM_TEXTURE2D_LINEAR_PITCH DEVICE_ATTRIBUTE_MAXIMUM_TEXTURE2D_LINEAR_PITCH} - Maximum 2D linear texture pitch in bytes</li>
     * <li>{@link #CU_DEVICE_ATTRIBUTE_MAXIMUM_TEXTURE2D_MIPMAPPED_WIDTH DEVICE_ATTRIBUTE_MAXIMUM_TEXTURE2D_MIPMAPPED_WIDTH} - Maximum mipmapped 2D texture width</li>
     * <li>{@link #CU_DEVICE_ATTRIBUTE_MAXIMUM_TEXTURE2D_MIPMAPPED_HEIGHT DEVICE_ATTRIBUTE_MAXIMUM_TEXTURE2D_MIPMAPPED_HEIGHT} - Maximum mipmapped 2D texture height</li>
     * <li>{@link #CU_DEVICE_ATTRIBUTE_COMPUTE_CAPABILITY_MAJOR DEVICE_ATTRIBUTE_COMPUTE_CAPABILITY_MAJOR} - Major compute capability version number</li>
     * <li>{@link #CU_DEVICE_ATTRIBUTE_COMPUTE_CAPABILITY_MINOR DEVICE_ATTRIBUTE_COMPUTE_CAPABILITY_MINOR} - Minor compute capability version number</li>
     * <li>{@link #CU_DEVICE_ATTRIBUTE_MAXIMUM_TEXTURE1D_MIPMAPPED_WIDTH DEVICE_ATTRIBUTE_MAXIMUM_TEXTURE1D_MIPMAPPED_WIDTH} - Maximum mipmapped 1D texture width</li>
     * <li>{@link #CU_DEVICE_ATTRIBUTE_STREAM_PRIORITIES_SUPPORTED DEVICE_ATTRIBUTE_STREAM_PRIORITIES_SUPPORTED} - Device supports stream priorities</