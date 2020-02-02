package org.redrune.network.codec.update.message.impl

import org.redrune.network.codec.update.message.UpdateMessage

/**
 * @author Tyluur <contact@kiaira.tech>
 * @since 2020-02-02
 */
data class FileRequestMessage(val indexId: Int, val archiveId: Int, val priority: Boolean) :
    UpdateMessage