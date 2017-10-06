package com.github.badoualy.telegram.tl.api

import com.github.badoualy.telegram.tl.TLObjectUtils.SIZE_CONSTRUCTOR_ID
import com.github.badoualy.telegram.tl.TLObjectUtils.SIZE_INT32
import com.github.badoualy.telegram.tl.TLObjectUtils.SIZE_INT64
import com.github.badoualy.telegram.tl.TLObjectUtils.computeTLStringSerializedSize
import com.github.badoualy.telegram.tl.core.TLObject
import com.github.badoualy.telegram.tl.serialization.TLDeserializer
import com.github.badoualy.telegram.tl.serialization.TLSerializer
import java.io.IOException

/**
 * stickerSet#cd303b41
 *
 * @author Yannick Badoual yann.badoual@gmail.com
 * @see <a href="http://github.com/badoualy/kotlogram">http://github.com/badoualy/kotlogram</a>
 */
class TLStickerSet() : TLObject() {
    @Transient
    var installed: Boolean = false

    @Transient
    var archived: Boolean = false

    @Transient
    var official: Boolean = false

    @Transient
    var masks: Boolean = false

    var id: Long = 0L

    var accessHash: Long = 0L

    var title: String = ""

    var shortName: String = ""

    var count: Int = 0

    var hash: Int = 0

    private val _constructor: String = "stickerSet#cd303b41"

    override val constructorId: Int = CONSTRUCTOR_ID

    constructor(
            installed: Boolean,
            archived: Boolean,
            official: Boolean,
            masks: Boolean,
            id: Long,
            accessHash: Long,
            title: String,
            shortName: String,
            count: Int,
            hash: Int
    ) : this() {
        this.installed = installed
        this.archived = archived
        this.official = official
        this.masks = masks
        this.id = id
        this.accessHash = accessHash
        this.title = title
        this.shortName = shortName
        this.count = count
        this.hash = hash
    }

    protected override fun computeFlags() {
        _flags = 0
        updateFlags(installed, 1)
        updateFlags(archived, 2)
        updateFlags(official, 4)
        updateFlags(masks, 8)
    }

    @Throws(IOException::class)
    override fun serializeBody(tlSerializer: TLSerializer) = with (tlSerializer)  {
        computeFlags()

        writeInt(_flags)
        writeLong(id)
        writeLong(accessHash)
        writeString(title)
        writeString(shortName)
        writeInt(count)
        writeInt(hash)
    }

    @Throws(IOException::class)
    override fun deserializeBody(tlDeserializer: TLDeserializer) = with (tlDeserializer)  {
        _flags = readInt()
        installed = isMask(1)
        archived = isMask(2)
        official = isMask(4)
        masks = isMask(8)
        id = readLong()
        accessHash = readLong()
        title = readString()
        shortName = readString()
        count = readInt()
        hash = readInt()
    }

    override fun computeSerializedSize(): Int {
        computeFlags()

        var size = SIZE_CONSTRUCTOR_ID
        size += SIZE_INT32
        size += SIZE_INT64
        size += SIZE_INT64
        size += computeTLStringSerializedSize(title)
        size += computeTLStringSerializedSize(shortName)
        size += SIZE_INT32
        size += SIZE_INT32
        return size
    }

    override fun toString() = _constructor

    override fun equals(other: Any?): Boolean {
        if (other !is TLStickerSet) return false
        if (other === this) return true

        return _flags == other._flags
                && installed == other.installed
                && archived == other.archived
                && official == other.official
                && masks == other.masks
                && id == other.id
                && accessHash == other.accessHash
                && title == other.title
                && shortName == other.shortName
                && count == other.count
                && hash == other.hash
    }
    companion object  {
        const val CONSTRUCTOR_ID: Int = 0xcd303b41.toInt()
    }
}